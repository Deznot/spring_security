<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <style>
        h3 {
            color: darkblue;
        }
    </style>
    <body>
        <h3>Information for Employees</h3>
        <security:authorize access="hasRole('HR')">
        <br><br>
        <input type="button" value="Salary" onclick="window.location.href='hr_info'">
    Only for HR staff
        </security:authorize>
        <security:authorize access="hasRole('MANAGER')">
        <br><br>
        <input type="button" value="Performance" onclick="window.location.href='manager_info'">
        Only for Managers staff
        </security:authorize>
    </body>
</html>