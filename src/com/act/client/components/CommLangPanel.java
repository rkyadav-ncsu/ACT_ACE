package com.act.client.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.act.common.ACEDefines;
import com.act.common.LangFluency;
import com.act.common.Language;
import com.act.common.SwingUtils;


public class CommLangPanel extends JPanel {
	
	private JComboBox<Language> cbVocLangComm;
	private JCheckBox chkVocLangRead;
	private JCheckBox chkVocLangWrite;
	private JCheckBox chkVocLangSpeak;
	
	public CommLangPanel(){                                                                                                                                                                                                                              
		
		//Language Name
		cbVocLangComm = new JComboBox();
		add(cbVocLangComm,SwingUtils.getConstraints(0, 0, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				0, 0, 0, 0));
		
		chkVocLangRead = new JCheckBox("Read");
		add(chkVocLangRead,SwingUtils.getConstraints(0, 1, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				0, 5, 0, 0));
		
		chkVocLangWrite = new JCheckBox("Write");
		add(chkVocLangWrite,SwingUtils.getConstraints(0, 2, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				0, 0, 0, 0));
		
		chkVocLangSpeak = new JCheckBox("Speak");
		add(chkVocLangSpeak,SwingUtils.getConstraints(0, 3, 1, 0,0, 
				GridBagConstraints.WEST, 
				GridBagConstraints.NONE, 
				0, 0, 0, 0));

	}

	public LangFluency getLanguage(){
		if (cbVocLangComm.getSelectedItem() == null ||
				cbVocLangComm.getSelectedItem().equals(ACEDefines.NA))
				return null;
		
		LangFluency lang = new LangFluency();
		
		lang.setLangName(cbVocLangComm.getSelectedItem().toString());
		lang.setCanRead(chkVocLangRead.isSelected());
		lang.setCanSpeak(chkVocLangSpeak.isSelected());
		lang.setCanWrite(chkVocLangWrite.isSelected());
		
		return lang;
	}
	
	public void setLanguageList(Vector<Language> vLang){ //TODO We do not need Language Object. Change to string
		if(vLang == null)
			return;
		cbVocLangComm.addItem(new Language());
		for (int i = 0; i < vLang.size(); i++) {
			
			cbVocLangComm.addItem(vLang.elementAt(i));
		}

		
	}		
	
	public void selectLanguageFluency(LangFluency langFluency){
		if (langFluency == null)
			return;
		
		String langName = langFluency.getLangName();
		
		for (int i = 0; i < cbVocLangComm.getItemCount(); i++) {
			if (cbVocLangComm.getItemAt(i).getLangName().equals(langName)){
				cbVocLangComm.setSelectedIndex(i);
				break;
			}
		}
		
		chkVocLangRead.setSelected(langFluency.isCanRead());
		chkVocLangSpeak.setSelected(langFluency.isCanSpeak());
		chkVocLangWrite.setSelected(langFluency.isCanWrite());
		
		return;
		
	}
}
