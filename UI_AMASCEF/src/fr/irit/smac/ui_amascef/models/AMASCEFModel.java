package fr.irit.smac.ui_amascef.models;

import java.util.List;

import fr.irit.smac.mas.AmasF;
import fr.irit.smac.mas.EnvironmentF;
import fr.irit.smac.mas.EnvironmentF.Expe;
import fr.irit.smac.modelui.AGFDataModel;
import fr.irit.smac.modelui.AGFModel;
import fr.irit.smac.modelui.NeighbourModel;
import fr.irit.smac.modelui.ReceiverModel;
import fr.irit.smac.modelui.SenderModel;
import fr.irit.smac.ui_amascef.main.AGFDataMain;

public class AMASCEFModel {

	private AmasF amas;
	
	public AMASCEFModel() {
		
	}
	
	
	public void run(String nbAgents, String nbFixes, String nbVariables, String nbTypes, String cross,String nbZoneCom) {
		String[] args = {nbAgents, nbFixes,nbVariables,nbTypes,cross,nbZoneCom};
		this.amas = new AmasF(new EnvironmentF( args, Expe.RANDOM),  args);
	}
	
	
	public AmasF getAmas() {
		return this.amas;
	}


	public void addListenerToAGFunction(AGFModel agf, String name) {
		this.amas.addPropertyChangeListener(agf, name);
	}


	public AGFDataModel createAGFDataModel(String name) {
		return this.amas.createAGFDataModel(name);
	}


	public NeighbourModel createNeighbourModel(String name) {
		return this.amas.createNeighbourModel(name);
	}


	public List<SenderModel> createSenderModel(String name) {
		return this.amas.createSenderModel(name);
	}


	public ReceiverModel createReceiverModel(String name) {
		return this.amas.createReceiverModel(name);
	}
}
