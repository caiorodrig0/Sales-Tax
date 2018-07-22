Application to solve the sales taxes coding problem.

<b>Tools used:</b><br/>
Spring boot - we created some microservice to the operations and can be used via browser or consumed by another application if neede.<br/>
MongoDB - we created some database transactions just to make some real case. <br/>
Java 1.8<br/><br/><br/>

<b>Exposed Services:</b><br/>
/sales/addProduct/{item}/{type}/{isImported}/{price}<br/>
/sales/deleteProduct/{item}/{isImported}/{price}<br/>
/sales/deleteAll/<br/>
/sales/listAll/<br/>
/sales/purchase/{item}/{quantity}/{isImported}/{price}<br/>

<b>How to run:</b><br/>
Run AppTest.java as Junit or run SpringBootInit.java as Java Application and access via URL.
