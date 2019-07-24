package fr.irit.smac.ui_amascef.models;

import java.util.Set;
import java.util.TreeSet;

import fr.irit.smac.learningdata.AmasLearning;
import fr.irit.smac.learningdata.EnvironmentLearning;
import fr.irit.smac.modelui.learning.DataLearningModel;
import fr.irit.smac.modelui.learning.InputLearningModel;

public class LearningDataModel {

	private AmasLearning amas;

	public LearningDataModel() {

	}
	public void run(String nbAgents) {
		String[] args = {nbAgents};
		this.amas = new AmasLearning(new EnvironmentLearning(args),args);
	}

	public AmasLearning getAmas() {
		return this.amas;
	}
	public Set<String> getInputsName(String name) {
		return this.amas.getInputsName(name);
	}
	public void addListenerToInput(String function,String input, InputLearningModel model) {
		this.amas.addListenerToInput(function,input,model);
	}

	public void addListenerToData(String function, String data, DataLearningModel model) {
		this.amas.addListenerToData(function,data,model);
	}
	public Set<String> getDatasName(String function) {
		if(this.amas == null) {
			return new TreeSet<String>();
		}
		else {
			return this.amas.getDatasNames(function);
		}
	}

}
