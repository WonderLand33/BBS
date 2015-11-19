//package test;
//
//import org.markdown4j.Plugin;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @description
// * @author: chenPeng
// * @date: 2015/9/9 9:46
// * Copyright © 2015/9/9 Shanghai Raxtone Software Co.,Ltd Allright Reserved
// */
//public class MyPlugin extends Plugin{
//
//        public MyPlugin() {
//            //bind your plugin with id
//            super("mypluginid");
//        }
//        @Override
//        public void emit(StringBuilder out, List<String> lines, Map<String, String> params) {
//            //read params and manage default value
//            String type = params.get("type");
//            if(type == null) {
//                type = "defaultType";
//            }
//            String clazz = params.get("class");
//            if(clazz == null) {
//                clazz = "defaultClass";
//            }
//
//            //process your plugin and return your text
//            out.append("...");
//        }
//}
