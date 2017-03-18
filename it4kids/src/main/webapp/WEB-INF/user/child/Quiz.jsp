<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Quiz</title>
</head>
<body>
	<%
		try {
			Runtime runtime = Runtime.getRuntime();
			Process exec = runtime.exec(new String[] { "java", "-cp", "/login/Quiz.jar", "Quiz" });
			int i = exec.waitFor();
			System.out.println(i);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	%>
	Quiz
</body>
</html>