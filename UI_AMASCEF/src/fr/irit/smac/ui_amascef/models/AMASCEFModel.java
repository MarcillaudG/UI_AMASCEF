package fr.irit.smac.ui_amascef.models;

import fr.irit.smac.mas.AmasF;
import fr.irit.smac.mas.EnvironmentF;
import fr.irit.smac.mas.EnvironmentF.Expe;
import fr.irit.smac.modelui.AGFModel;

public class AMASCEFModel {

	private AmasF amas;
	
	public AMASCEFModel() {
		
	}
	
	
	public void run(String nbAgents, String nbFixes, String nbVariables, String nbTypes, String cross) {
		String[] args = {nbAgents, nbFixes,nbVariables,nbTypes,cross};
		this.amas = new AmasF(new EnvironmentF( args, Expe.RANDOM),  args);
	}
	
	
	public AmasF getAmas() {
		return this.amas;
	}


	public void addListenerToAGFunction(AGFModel agf, String name) {
		this.amas.addPropertyChangeListener(agf, name);
	}
}
