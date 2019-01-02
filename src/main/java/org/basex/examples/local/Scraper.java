/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.basex.examples.local;

import java.net.MalformedURLException;
import org.basex.core.BaseXException;

/**
 *
 * @author thufir
 */
public interface Scraper {

    public void fetch() throws BaseXException, MalformedURLException;

}
