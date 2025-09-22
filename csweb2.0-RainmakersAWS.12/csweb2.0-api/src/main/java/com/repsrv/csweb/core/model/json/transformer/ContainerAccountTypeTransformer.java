package com.repsrv.csweb.core.model.json.transformer;

import com.repsrv.csweb.core.util.ContainerAccountType;
import flexjson.transformer.AbstractTransformer;

public class ContainerAccountTypeTransformer extends AbstractTransformer{

	@Override
	public void transform(Object object) {

		getContext().writeOpenObject();
		
        ContainerAccountType type = (ContainerAccountType)object;
        
        getContext().writeName("label");


        if (type != null) {
            getContext().transform(type.getLabel());
        } else {
            getContext().write("null");
        }

        getContext().writeComma();
        getContext().writeName("detail");

        if (type != null) {
            getContext().transform(type.getDetail());
        } else {
            getContext().write("null");
        }

        getContext().writeCloseObject();
	}
}
