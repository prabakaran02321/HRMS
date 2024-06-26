package com.hrms.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.hrms.models.Payroll;

public class PayrollRepo {

    private DatabaseRepo db;

    public PayrollRepo() {
        this.db = new DatabaseRepo();
    }

    public int save(Payroll payroll) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("employee_id", payroll.getEmployeeId());
        map.put("pay_date", payroll.getPayDate());
        map.put("gross_pay", payroll.getGrossPay());
        map.put("deductions", payroll.getDeductions());
        map.put("net_pay", payroll.getNetPay());
        map.put("payment_mode", payroll.getPaymentMode());

        return db.insertDataIntoTable("payroll", map);
    }

    public List<Payroll> getPayrolls(int start, int total) {
        List<Payroll> list = new ArrayList<>();
        try {
            ResultSet rs = db.selectStarFromTablePagination("payroll", start, total);
            while (rs.next()) {
                Payroll payroll = new Payroll();
                payroll.setPayrollId(rs.getInt("payroll_id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setPayDate(rs.getString("pay_date"));
                payroll.setGrossPay(rs.getDouble("gross_pay"));
                payroll.setDeductions(rs.getDouble("deductions"));
                payroll.setNetPay(rs.getDouble("net_pay"));
                payroll.setPaymentMode(rs.getString("payment_mode"));
                list.add(payroll);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        return db.getNoOfRecords("payroll", "payroll_id");
    }

    public int delete(int payrollId) {
        return db.deleteDataFromTable("payroll", "payroll_id='" + payrollId + "'");
    }
}
