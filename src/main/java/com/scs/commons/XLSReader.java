package com.scs.commons;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.scs.driverfactory.BaseTest;

public class XLSReader {

    private final Fillo fillo;
    private final String filePath;
    private static final String testdataPath = BaseTest.externalSheetPath();
    
    private Connection connection;

    public XLSReader(String filePath) {
        fillo = new Fillo();
        this.filePath = filePath;
    }

    public Recordset getTests(String query) {
    	 Recordset recordset=null;
        try {
            connection = fillo.getConnection(this.filePath);
            recordset= connection.executeQuery(query);
        
        } catch (FilloException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
		return recordset;
        
        
    }
}