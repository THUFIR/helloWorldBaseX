package org.basex.examples.local;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.List;

public class Database {

    private static final Logger LOG = Logger.getLogger(App.class.getName());
    private Properties properties = new Properties();
    private final Context context = new Context();

    private Database() {
    }

    public Database(Properties properties) {
        this.properties = properties;
        LOG.fine(properties.toString());
    }

    private void list() {
        try {
            LOG.info(new List().execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.FINE, null, ex);
            LOG.info("no databases");
        }
    }

    public void CreateFromScratch() {
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");
        String dataPath = properties.getProperty("dataPath");

        list();
        try {
            new DropDB(databaseName).execute(context);
        } catch (BaseXException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.FINE, null, ex);
            LOG.fine("no databases to drop");
        }
        list();
        try {
            new CreateDB(databaseName, dataPath).execute(context);
        } catch (BaseXException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.FINE, null, ex);
            LOG.severe("cannot create database");
        }
        list();
    }

    private void createDrop() throws BaseXException {
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");
        list();
        new CreateDB(databaseName, databasePath).execute(context);
        list();
        new DropDB(databaseName).execute(context);
        list();
    }

    public void tryDrop() {
        try {
            drop();
        } catch (BaseXException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.FINE, null, ex);
        }
    }

    private void drop() throws BaseXException {
        String databaseName = properties.getProperty("databaseName");
        String databasePath = properties.getProperty("databasePath");
        new DropDB(databaseName).execute(context);
    }
}
