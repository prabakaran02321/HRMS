package com.hrms.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.hrms.models.Payroll;
import com.hrms.repository.PayrollRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PayrollService {

	private PayrollRepo payrollRepo;
	private Payroll payroll;

	public PayrollService() {
		payrollRepo = new PayrollRepo();
		payroll = new Payroll();
	}

	public void createPayroll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		payroll.setEmployeeId(Integer.parseInt(request.getParameter("employeeid")));
		payroll.setPayDate(request.getParameter("paydate"));
		payroll.setGrossPay(Double.parseDouble(request.getParameter("grosspay")));
		payroll.setDeductions(Double.parseDouble(request.getParameter("deductions")));
		payroll.setNetPay(Double.parseDouble(request.getParameter("netpay")));
		payroll.setPaymentMode(request.getParameter("paymentmode"));

		int result = payrollRepo.save(payroll);

		if (result > 0) {
			response.getWriter().write("Payroll record added successfully");
		} else {
			response.getWriter().write("Failed to add payroll record");
		}
	}

	public void getAllRecords(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int page = 1;
		int recordsPerPage = 3;
		int noOfRecords = payrollRepo.getNoOfRecords();
		int noOfPages = (int) Math.ceil((double) noOfRecords / recordsPerPage);

		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		if (page <= noOfPages) {
			List<Payroll> list = payrollRepo.getPayrolls((page - 1) * recordsPerPage, recordsPerPage);

			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();

			out.println("Page " + page + " of " + noOfPages);
			out.println("===================================");
			for (Payroll payroll : list) {
				out.println("Payroll ID: " + payroll.getPayrollId());
				out.println("Employee ID: " + payroll.getEmployeeId());
				out.println("Pay Date: " + payroll.getPayDate());
				out.println("Gross Pay: " + payroll.getGrossPay());
				out.println("Deductions: " + payroll.getDeductions());
				out.println("Net Pay: " + payroll.getNetPay());
				out.println("Payment Mode: " + payroll.getPaymentMode());
				out.println("===================================");
			}
			out.println("Current Page: " + page);
			out.println("Total Pages: " + noOfPages);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Payroll records have only " + noOfPages + " pages");
		}
	}

	public void deletePayroll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int payrollId;
		try {
			payrollId = Integer.parseInt(request.getParameter("payrollid"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid payroll ID");
			return;
		}

		int result = payrollRepo.delete(payrollId);

		if (result > 0) {
			response.getWriter().write("Payroll record deleted successfully");
		} else {
			response.getWriter().write("Failed to delete payroll record");
		}
	}
}
