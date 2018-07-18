package com.nuaa.health.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuaa.health.svm.svm_make_node;
import com.nuaa.health.svm.svm_train;
import com.nuaa.health.util.GenericJsonResult;
import com.nuaa.health.util.HResult;

import libsvm.svm;
import libsvm.svm_model;

@RestController
@RequestMapping(value = "svm")
public class SvmController {
	@RequestMapping(value = "/{type}/predict",method=RequestMethod.POST)
    public GenericJsonResult<Double> health_predict(@RequestParam(value = "args", required = true) String args,@PathVariable String type) {
		svm_model model = null;
		GenericJsonResult<Double> res = new GenericJsonResult<Double>(HResult.S_OK);
		try {
			File file = null;
			if (type.equals("eye"))
				file = ResourceUtils.getFile("classpath:eye_model.txt");
			else if((type.equals("health")))
				file = ResourceUtils.getFile("classpath:health_model.txt");
			else
				res.setStatus(HResult.E_ERROR_PARAMETER);
			model = svm.svm_load_model(file.getPath());
			res.setData(svm.svm_predict(model, svm_make_node.makeNode(args)));
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
}