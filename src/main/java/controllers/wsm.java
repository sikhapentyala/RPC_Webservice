package controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.DataMatrix;
import model.MCDAResponse;
import model.WSMDataMatrix;

/**
 * Servlet implementation class wsm
 */
@WebServlet("/wsm")
public class wsm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	WSMComputation wsmComputationObject;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public wsm() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			response.setContentType("application/json"); // set content-type to json
			response.setHeader("MCDA-version", "beta-0.1"); // create custom header attribute for version
			// recieve JSON response, convert this to DataMatrix POJO

			ObjectMapper mapper = new ObjectMapper();
			DataMatrix inputDataMatrix = null;
			if (request.getParameter("file") != null) {
				// convert json file into DataMatrix
				inputDataMatrix = mapper.readValue(new File("c:\\user.json"), DataMatrix.class);
			} else {
				// Get from http request
				String jsonString = "";
				inputDataMatrix = mapper.readValue(jsonString, DataMatrix.class);
			}

			WSMDataMatrix wsmDataMatrix = new WSMDataMatrix(inputDataMatrix);
			wsmComputationObject = new WSMComputation(wsmDataMatrix);
			wsmComputationObject.computeScores();

			MCDAResponse computedResults = wsmComputationObject.generateResponse();

			String jsonInString = mapper.writeValueAsString(computedResults);
			// System.out.println(jsonInString);

			// set HTTP header : best item, its score,
			// set response header
			response.setHeader("top-recommended-item", computedResults.getItems().get(0).getItem().getName()); // set
																												// header
																												// attribute
			// for
			// best item name
			response.setHeader("top-recommended-item-score",
					String.valueOf(computedResults.getItems().get(0).getScore())); // set
																					// header
																					// attribute
																					// for
																					// best
																					// item
																					// score
			response.setHeader("average-computed-score", String.valueOf(computedResults.getAverageScore())); // set

			response.setHeader("standard_deviation-score", String.valueOf(computedResults.getStandardDeviation()));
			response.getWriter().write(jsonInString);
			response.getWriter().flush();
			System.out.println(response.toString());

		} catch (Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}

		// return MCDA response

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
