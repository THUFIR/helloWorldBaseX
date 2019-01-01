package org.basex.examples.local;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;
//import oracle.xml.xquery.OXQDataSource;
import java.lang.Object;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();
    private final Context context = new Context();

    public static void main(String[] args) throws BaseXException, IOException {
        LOG.fine("starting..");
        new App().helloWorld();
    }

    private void list() throws BaseXException {
        LOG.info(new List().execute(context));
    }

    private void helloWorld() throws BaseXException, IOException {
        properties.loadFromXML(App.class.getResourceAsStream("/basex.xml"));
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");

        list();
        new CreateDB(databaseName, databasePath).execute(context);
        list();
        new DropDB(databaseName).execute(context);
        list();

    }

    /*
    private void oracleXQJ() throws XQException {
//        OXQDataSource ds = new OXQDataSource();
        XQConnection con = ds.getConnection();
        String query = "<hello-world>{1 + 1}</hello-world>";
        XQPreparedExpression expr = con.prepareExpression(query);
        XQSequence result = expr.executeQuery();
        // prints "<hello-world>2</hello-world>"
        System.out.println(result.getSequenceAsString(null));
        result.close();
        expr.close();
        con.close();
    }
*/
}



/*
    <dependency>
      <groupId>org.exist-db</groupId>
      <artifactId>exist-core</artifactId>
      <version>5.0.0-RC5</version>
    </dependency>

You will also need to add these repository to (or create) the repositories section of your Maven pom.xml file:

    <repository>
      <id>exist-db</id>
      <url>http://repo.evolvedbinary.com/content/repositories/exist-db/</url>
      <releases><enabled>true</enabled></releases>
      <snapshots><enabled>false</enabled></snapshots>
    </repository>
    <repository>
      <id>exist-db-snapshots</id>
      <url>http://repo.evolvedbinary.com/content/repositories/exist-db-snapshots/</url>
      <releases><enabled>false</enabled></releases>
      <snapshots><enabled>true</enabled></snapshots>
    </repository>
*/