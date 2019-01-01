package org.basex.examples.local;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Close;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.CreateIndex;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.DropIndex;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.Open;

public final class App {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private final Properties properties = new Properties();

    public static void main(String[] args) throws BaseXException {
        LOG.fine("starting..");
        java.util.List<String> stringList = new ArrayList<>();
        String string = "list";
        stringList.add(string);
        new App().foo(stringList);
    }

    private void foo(java.util.List<String> StringList) throws BaseXException {
        
        // Database context.
        Context context = new Context();

        System.out.println("=== RunCommands ===");

        // Create a database from a local or remote XML document or XML String
        System.out.println("\n* Create a database.");

        new CreateDB("DBExample", "/input.xml").execute(context);

        // Close and reopen the database
        System.out.println("\n* Close and reopen database.");

        new Close().execute(context);
        new Open("DBExample").execute(context);

        // Additionally create a full-text index
        System.out.println("\n* Create a full-text index.");

        new CreateIndex("fulltext").execute(context);

        // Show information on the currently opened database
        System.out.println("\n* Show database information:");

        System.out.print(new InfoDB().execute(context));

        // Drop indexes to save disk space
        System.out.println("\n* Drop indexes.");

        new DropIndex("text").execute(context);
        new DropIndex("attribute").execute(context);
        new DropIndex("fulltext").execute(context);

        // Drop the database
        System.out.println("\n* Drop the database.");

        new DropDB("DBExample").execute(context);

        // Show all existing databases
        System.out.println("\n* Show existing databases:");

        System.out.print(new List().execute(context));

        // Close the database context
        context.close();
    }

    
}
