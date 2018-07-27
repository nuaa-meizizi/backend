package com.nuaa.health.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dmg.pmml.FieldName;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.svm.svm_make_node;
import com.nuaa.health.svm.svm_train;
import com.nuaa.health.util.ClassificationModel;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

import libsvm.svm;
import libsvm.svm_model;

@RestController
@RequestMapping(value = "svm")
public class SvmController {
	@RequestMapping(value = "/{type}/predict",method=RequestMethod.POST)
    public GenericJsonResult<String> health_predict(@RequestParam(value = "args", required = true) String args,@PathVariable String type) {
		svm_model model = null;
		GenericJsonResult<String> res = new GenericJsonResult<String>(HResult.S_OK);
		try {
			File file = null;
			if (type.equals("eye")) {
				file = ResourceUtils.getFile("classpath:eye_model.txt");
				model = svm.svm_load_model(file.getPath());
				res.setData(Double.toString(svm.svm_predict(model, svm_make_node.makeNode(args))));
			}
			else if((type.equals("health"))) {
				file = ResourceUtils.getFile("classpath:health.pmml");
				ClassificationModel clf = new ClassificationModel(file.getPath());
				Map<FieldName, Number> waitPreSample = getFieldNameForHealth(args);
				res.setData(clf.predict(waitPreSample).toString());
			}
			else {
				res.setStatus(HResult.E_ERROR_PARAMETER);
				return res;
			}

		} catch (IOException e) {
			res.setStatus(HResult.E_FILE_EXCEPTION);
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value = "/{type}/train",method=RequestMethod.GET)
    public GenericJsonResult<String> health_tarin(@PathVariable String type) {
		GenericJsonResult<String> res = new GenericJsonResult<String>(HResult.S_OK);
		try {
			File train_file = null;
			if (type.equals("eye"))
				train_file = ResourceUtils.getFile("classpath:eye_train.txt");
			else if((type.equals("health")))
				train_file = ResourceUtils.getFile("classpath:health_train.txt");
			else
				res.setStatus(HResult.E_ERROR_PARAMETER);
			String[] arg = { train_file.getPath(), "d:\\"+type+"_model.txt"};
			svm_train.main(arg);
		} catch (IOException e) {
			res.setStatus(HResult.E_FILE_EXCEPTION);
			e.printStackTrace();
		}
		return res;
	}
	
	private Map<FieldName, Number> getFieldNameForHealth(String arg) {
		String[] args = arg.split(" ");
		Map<FieldName, Number> waitPreSample = new HashMap<>();
        waitPreSample.put(new FieldName("temperature"), Float.parseFloat(args[0]));
        waitPreSample.put(new FieldName("weight"), Integer.parseInt(args[2]));
        waitPreSample.put(new FieldName("heartrate"), Integer.parseInt(args[1]));
        waitPreSample.put(new FieldName("D"), Integer.parseInt(args[3]));
        waitPreSample.put(new FieldName("S"), Integer.parseInt(args[4]));
		return waitPreSample;
	}
}