package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;

import java.nio.file.Paths;

public class BudgetPlanner {
	public static void main(String[] args)
	{
		new BudgetPlannerImporter().importCsv(Paths.get("src/main/resources/account_payment.csv"));
	}
}
