package com.repsrv.csweb.core.flexjson.serlizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.repsrv.csweb.core.flexjson.view.AttributeMapping;
import com.repsrv.csweb.core.flexjson.view.View;

import java.lang.reflect.Field;

import flexjson.JSONSerializer;

/**
 * Default implementation of {@link SerializerGenerator}.
 */
public class SerializerGeneratorImpl implements SerializerGenerator{

    private List<String> globalIncludes;
    private List<String> globalExcludes;


    public SerializerGeneratorImpl(List<String> globalIncludes, List<String> globalExcludes){
        this.globalIncludes = globalIncludes;
        this.globalExcludes = globalExcludes;
    }

    /**
     * Returns a list of the field methods for a given class, including the
     * inherited fields.
     *
     * @param type class type that will be introspected
     * @return a list of all the fields in the class hierarchy.
     */
    private static List<Field> getInheritedPrivateFields(Class<?> type){
        List<Field> result = new ArrayList<>();

        Class<?> i = type;
        while(i != null && i != Object.class){
            result.addAll(Arrays.asList(i.getDeclaredFields()));
            i = i.getSuperclass();
        }

        return result;
    }

    @Override
    public Serializer generate(View view){
        JSONSerializer serializer = new JSONSerializer();

        // Add transformers
        //serializer.transform(new URLTransformer(), URL.class);

        List<String> includes = Stream.concat(
                globalIncludes.stream().map(property -> "*." + property),
                view.getGlobalIncludes().stream().map(property -> "*." + property))
                .collect(Collectors.toList());

        serializer.setIncludes(includes.stream().map(property -> "*." + property).collect(Collectors.toList()));

        // Add set global excludes
        serializer.setExcludes(Stream.concat(
                globalExcludes.stream().map(property -> "*." + property),
                view.getGlobalExcludes().stream().map(property -> "*." + property))
                .collect(Collectors.toList()));

        for(AttributeMapping mapping : view.getMappings().values()){
            List<String> tempIncludes = mapping.getRules().stream().map(s -> {
                if(s.getPrefix().isEmpty())
                    return s.getAttribute();
                else
                    return s.getPrefix() + "." + s.getAttribute();
            }).collect(Collectors.toList());
            serializer.setIncludes(tempIncludes);

            if(mapping.getClazz() != null)
                serializer.setExcludes(getExcludesForObject(mapping.getClazz(), mapping.getPrefix(), tempIncludes, includes));
        }

        return new Serializer(view.getId(), serializer);
    }

    /**
     * Gets a list of exclude expressions based on the given class and the global excludes and includes.
     *
     * @param clazz         the class to be inspected to exclude all fields but the ones sent in the include list.
     * @param attributeName the attribute name to be used as prefix in the exclude expressions.
     * @param include       the list of fields to include.
     */
    @SuppressWarnings("rawtypes")
    protected List<String> getExcludesForObject(Class clazz, String attributeName, List<String> include, List<String> globalIncludes){
        String prefix = "";

        if(attributeName != null && !attributeName.equals("")){
            prefix = attributeName + ".";
        }
        List<String> excludes = new LinkedList<>();

        List<Field> fields = getInheritedPrivateFields(clazz);
        for(Field field : fields){
            String fieldName = field.getName();

            if(!globalIncludes.contains(fieldName) && (include == null || !include.contains(fieldName))){
                excludes.add(prefix + fieldName);
            }
        }

        return excludes;
    }

    public void setGlobalIncludes(List<String> globalIncludes){
        this.globalIncludes = globalIncludes;
    }

    public void setGlobalExcludes(List<String> globalExcludes){
        this.globalExcludes = globalExcludes;
    }
}