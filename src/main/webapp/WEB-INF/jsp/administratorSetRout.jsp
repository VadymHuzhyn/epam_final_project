<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<body>
<form action="administrator_set_rout_mapping" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="order"/></th>
            <th><fmt:message key="station.name"/></th>
            <th><fmt:message key="arrivalDate"/></th>
            <th><fmt:message key="dispatchDate"/></th>
        </tr>
        <tr>
            <input type="hidden" name="routs_id" value="${routs_id}">
            <td><input name="station_order"></td>
            <td><select name="station_station">
                <c:forEach items="${station_list}" var="station">
                    <option value="${station.stationId}"><c:out value="${station.station}"/></option>
                </c:forEach>
            </select></td>
            <td><input name="station_arrival_date" type="datetime-local"></td>
            <td><input name="station_dispatch_data" type="datetime-local"></td>
        </tr>
    </table>
    <p>
    <td>
        <form action="administrator_set_rout_mapping" method="GET">
            <input type="hidden" name="routs_id" value="${routs_id}">
            <input type="submit" name="add_rout_mapping" value="<fmt:message key="admin.addRout"/>">
        </form>
    </td>
    </p>
</form>
<p>
<form action="administrator_details_set_rout" method="GET">
    <input type="hidden" name="routs_id" value="${routs_id}">
    <input type="submit"  value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>