package logic;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import trimatrix.db.DAOLayer;
import trimatrix.db.ImportTemplates;
import trimatrix.logic.ImportLogic;
import trimatrix.logic.LogicLayer;
import trimatrix.utils.ContextStatic;

public class ImportLogicTest {
	private ApplicationContext context = ContextStatic.getInstance();
	private ImportLogic importLogic = (LogicLayer.getFromApplicationContext(context)).getImportLogic();
	private DAOLayer daoLayer = DAOLayer.getFromApplicationContext(context);

	@Test
	public void testResultListImport() throws Exception {
		File excel = new File("testingdata/2010dextroenergytriathlonitutr.xls");
		ImportTemplates importTemplate = new ImportTemplates();
		importTemplate.setMapping("[1, 11, 4, 3, 6, 8, 10]");
		importTemplate.setStartingRow(2);
		ImportLogic.ResultListData resultListData = importLogic.readResultExcel(FileUtils.readFileToByteArray(excel), importTemplate);
		System.out.println(resultListData.data.size());
		System.out.println(resultListData.bestBiker);
		System.out.println(resultListData.bestRunner);
		System.out.println(resultListData.bestSwimmer);
	}
}
