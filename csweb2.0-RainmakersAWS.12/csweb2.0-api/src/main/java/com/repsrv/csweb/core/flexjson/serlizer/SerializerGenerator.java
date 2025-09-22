package com.repsrv.csweb.core.flexjson.serlizer;

import com.repsrv.csweb.core.flexjson.view.View;

public interface SerializerGenerator{

    /**
     * Parses the given view into a ready to use {@link Serializer}.
     *
     * @param view the view object.
     *
     * @return a json serializer.
     */
    Serializer generate(View view);
}
