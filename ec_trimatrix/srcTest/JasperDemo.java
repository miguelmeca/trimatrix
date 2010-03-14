import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXmlExporter;

public class JasperDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport("report.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JREmptyDataSource());
			JRXmlExporter xmlExporter = new JRXmlExporter();
			xmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			xmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report.xml");
			xmlExporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}
