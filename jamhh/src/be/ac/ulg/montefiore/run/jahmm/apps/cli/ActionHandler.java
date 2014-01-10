/*
 * Copyright (c) 2004-2009, Jean-Marc François. All Rights Reserved.
 * Licensed under the New BSD license.  See the LICENSE file.
 */

package be.ac.ulg.montefiore.run.jahmm.apps.cli;


import java.io.FileNotFoundException;
import java.io.IOException;

import be.ac.ulg.montefiore.run.jahmm.io.FileFormatException;

abstract class ActionHandler
{
	public static enum Actions {
		HELP("-help", HelpActionHandler.class),
		PRINT("print", PrintActionHandler.class),
		CREATE("create", CreateActionHandler.class),
		BW("learn-bw", BWActionHandler.class),
		KMEANS("learn-kmeans", KMeansActionHandler.class),
		GENERATE("generate", GenerateActionHandler.class),
		KL("distance-kl", KLActionHandler.class);
		
		private String argument;
		private Class<? extends ActionHandler> handler;
		
		Actions(String argument, Class<? extends ActionHandler> handler) {
			this.argument = argument;
			this.handler = handler;
		}
		
		public String toString() {
			return argument;
		}
		
		public Class<? extends ActionHandler> handler() {
			return handler;
		}
	};

	
	public void parseArguments(String args[])
	throws WrongArgumentsException
	{
		CommandLineArguments.parse(args);
	}

	
	abstract public void act()
	throws FileNotFoundException, IOException, FileFormatException,
	AbnormalTerminationException;
}
