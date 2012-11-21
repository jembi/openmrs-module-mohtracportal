<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:htmlInclude file="/moduleResources/@MODULE_ID@/tracportal.css" />

<script type="text/javascript">
	var $ = jQuery.noConflict();
	$(document).ready(function() {
		// Hide the OpenMRS logo for 1.6.x version
		//var logo = $('img[src="${pageContext.request.contextPath}/images/openmrs_logo_large.gif"]');
		// Hide the OpenMRS logo for 1.9.x version
		var logo = $('img[src="${pageContext.request.contextPath}/images/openmrs_logo_white_large.png"]');
		
		logo.css('display', 'none');
		
		// Remove the 3 br's
		logo.next('br').remove();
		logo.next('br').remove();
		logo.next('br').remove();
	});
</script>

<c:choose>
	<c:when test="${model.authenticatedUser != null}">
		<div>
			<div style="width: 74%; float: left;">
				<div class="box">
					<c:choose>
						<c:when test="${model.showName != 'false'}">
							<spring:message code="welcomeUser" arguments="${model.authenticatedUser.personName.givenName},${fn:substring(pageContext.request.contextPath,1,-1)}" />
						</c:when>
						<c:otherwise>
							<spring:message code="welcome" arguments="${fn:substring(pageContext.request.contextPath,1,-1)}" />
						</c:otherwise>
					</c:choose>
					<c:if test="${model.customText != ''}">
						${model.customText}
					</c:if>
				</div>
				<br/>
				
				<openmrs:portlet id="findPatient" url="findPatient" parameters="size=full|postURL=patientDashboard.form|showIncludeVoided=false|viewType=shortEdit|hideAddNewPatient=true" />
				
			</div>
			<div style="width: 24%; float: right;">
				<b class="boxHeader">
					<spring:message code="@MODULE_ID@.usefulLinks" />
				</b>
				<div class="box">
					
					<openmrs:hasPrivilege privilege="View TRAC Portal modules functionalities">

						<openmrs:extensionPoint pointId="org.openmrs.tracmodule.list" type="html">
							<openmrs:hasPrivilege privilege="${extension.requiredPrivilege}">
						
								<div class="adminMenuList">
									<h4><spring:message code="${extension.title}"/></h4>
									<ul id="menu">
										<c:forEach items="${extension.links}" var="link">
											<c:choose>
												<c:when test="${fn:startsWith(link.key, 'module/')}">
													<%-- Added for backwards compatibility for most links --%>
													<li><a href="${pageContext.request.contextPath}/${link.key}"><spring:message code="${link.value}"/></a></li>
												</c:when>
												<c:otherwise>
													<%-- Allows for external absolute links  --%>
													<li><a href='<c:url value="${link.key}"/>'><spring:message code='${link.value}'/></a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</div>
							</openmrs:hasPrivilege>
						</openmrs:extensionPoint>

					</openmrs:hasPrivilege>
					
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
		
		
	</c:when>
	<c:otherwise>
		<spring:message code="welcome" arguments="${fn:substring(pageContext.request.contextPath,1,-1)}" />
		<c:if test="${model.showLogin == 'true'}">
			<br/>
			<openmrs:portlet url="login" parameters="redirect=${model.redirect}" />
		</c:if>
	</c:otherwise>
</c:choose>