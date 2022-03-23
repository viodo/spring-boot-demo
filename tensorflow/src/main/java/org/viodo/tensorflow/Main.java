package org.viodo.tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.proto.framework.SignatureDef;
import org.tensorflow.types.TFloat32;
import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/27 10:34
 */
public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        System.out.println(TensorFlow.version());
        // python 转换
        // model = tf.keras.models.load_model('test.h5')
        // model.save('./models/test')
        // java调用
        float[][] testData = {{}};
        SavedModelBundle bundle = SavedModelBundle.load(Paths.get(Objects.requireNonNull(Main.class.getResource("/models/tfmodel")).toURI()).toString(),
                "serve");
        SignatureDef modelInfo = bundle.metaGraphDef().getSignatureDefMap().get("serving_default");
        System.out.println(modelInfo.getInputsMap());
        String inputKey = modelInfo.getInputsMap().get("key").toString();
        String outputKey = modelInfo.getOutputsMap().get("key").toString();
        TFloat32 result = (TFloat32) bundle.session().runner()
                .feed(inputKey, TFloat32.tensorOf(StdArrays.ndCopyOf(testData)))
                .fetch(outputKey)
                .run()
                .get(0);
        System.out.println(result.shape());

//
//
//        Graph graph = new Graph();
//        byte[] graphBytes = IOUtils.toByteArray(new FileInputStream("E:/download/yiyuzheng.pb"));
//        graph.importGraphDef(GraphDef.parseFrom(graphBytes));
//
//        //SavedModelBundle savedModelBundle = SavedModelBundle.load("E:/download/modelfile/", "serve");
//
//        //Session session = savedModelBundle.session();
//        Session session = new Session(graph);
//        TFloat32 x = TFloat32.tensorOf(StdArrays.ndCopyOf(testData2));
//        TFloat32 result = (TFloat32) session.runner().feed("input_dense_input", x).fetch("output_dense/Sigmoid").run().get(0);
//        System.out.println(result.getFloat(0, 0));
    }
}