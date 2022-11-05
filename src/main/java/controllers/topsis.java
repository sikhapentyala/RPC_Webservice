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
import model.TOPSISDataMatrix;
import model.WSMDataMatrix;

/**
 * Servlet implementation class topsis
 */
@WebServlet("/topsis")
public class topsis extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopsisComputation topsisComputationObject;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public topsis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		response.setContentType("application/json"); // set content-type to json
		response.setHeader("MCDA-version", "beta-0.1"); // create custom header attribute for version
		
		try {
			ObjectMapper mapper = new ObjectMapper();
		DataMatrix inputDataMatrix = null;
		if(request.getParameter("file") != null)
		{
			//convert json file into DataMatrix
			inputDataMatrix = mapper.readValue(new File("c:\\user.json"), DataMatrix.class);
		}
		else
		{
			//Get from http request - invoked by composite service
			String jsonString = "";
			inputDataMatrix = mapper.readValue(jsonString, DataMatrix.class);
		}
		
		
		TOPSISDataMatrix topsisDataMatrix = new TOPSISDataMatrix(inputDataMatrix);
		topsisComputationObject = new TopsisComputation(topsisDataMatrix);
		topsisComputationObject.computeScores();
		MCDAResponse computedResults = topsisComputationObject.generateResponse();
		
        
        String jsonInString = mapper.writeValueAsString(computedResults);
        //System.out.println(jsonInString);
        
        //set HTTP header : best item, its score,
		// set response header
		response.setHeader("top-recommended-item", computedResults.getItems().get(0).getItem().getName()); // set header attribute
																							// for
																							// best item name
		response.setHeader("top-recommended-item-score", String.valueOf(computedResults.getItems().get(0).getScore())); // set
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
        
		}
		catch(Exception ex) {
			System.out.println(ex);
			throw new IOException(ex.getMessage());
		}
		//return MCDA response
	}



}
