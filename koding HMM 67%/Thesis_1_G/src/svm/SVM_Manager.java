///*
// * Copyright 2017 gazandic.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package svm;
//
//import ArabicOCR.ArabicOCR;
//import ArabicOCR.DataTrain;
//import HMMFInal.Const;
//import HMMFInal.HiddenMarkov;
//import HMMFInal.Serialize;
//import image.BinaryImageShell;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.StringTokenizer;
//import java.util.Vector;
//import libsvm.svm_model;
//import libsvm.svm_node;
//import libsvm.svm_parameter;
//import libsvm.svm_problem;
//
///**
// *
// * @author gazandic
// */
//public class SVM_Manager {
//    
//    private static svm_problem read_problem(int[][] data) throws IOException
//    {
//            Vector<Double> vy = new Vector<Double>();
//            Vector<svm_node[]> vx = new Vector<svm_node[]>();
//            int max_index = 0;
//
//            while(true)
//            {
//                    String line = fp.readLine();
//                    if(line == null) break;
//
//                    StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
//
//                    vy.addElement(atof(st.nextToken()));
//                    int m = st.countTokens()/2;
//                    svm_node[] x = new svm_node[m];
//                    for(int j=0;j<m;j++)
//                    {
//                            x[j] = new svm_node();
//                            x[j].index = atoi(st.nextToken());
//                            x[j].value = atof(st.nextToken());
//                    }
//                    if(m>0) max_index = Math.max(max_index, x[m-1].index);
//                    vx.addElement(x);
//            }
//
//            svm_problem prob = new svm_problem();
//            prob.l = vy.size();
//            prob.x = new svm_node[prob.l][];
//            for(int i=0;i<prob.l;i++)
//                    prob.x[i] = vx.elementAt(i);
//            prob.y = new double[prob.l];
//            for(int i=0;i<prob.l;i++)
//                    prob.y[i] = vy.elementAt(i);
//
//            if(param.gamma == 0 && max_index > 0)
//                    param.gamma = 1.0/max_index;
//
//            if(param.kernel_type == svm_parameter.PRECOMPUTED)
//                    for(int i=0;i<prob.l;i++)
//                    {
//                            if (prob.x[i][0].index != 0)
//                            {
//                                    System.err.print("Wrong kernel matrix: first column must be 0:sample_serial_number\n");
//                                    System.exit(1);
//                            }
//                            if ((int)prob.x[i][0].value <= 0 || (int)prob.x[i][0].value > max_index)
//                            {
//                                    System.err.print("Wrong input format: sample_serial_number out of range\n");
//                                    System.exit(1);
//                            }
//                    }
//
//            fp.close();
//    }
//    public static void train() {
//        
//        // create model directory for each data
//        File dataset = new File(Const.TRAIN_DATA);
//        System.out.println(dataset.getAbsolutePath());
//        System.out.println(dataset.listFiles());
//        for (File file : dataset.listFiles()) {
//            if (file.isDirectory()) {
//                String name = file.getName();
//                File model = new File(Const.MODELSVM_DATA + name);
//                if (!model.exists()) {
//                    model.mkdir();
//                }
//            }
//        }
//        // write model to each destined folder
//        for (File file : dataset.listFiles()) {
//            if (file.isDirectory()) {
//                String name = file.getName();
//                int[][] finaldata = new int[0][];
//                for (File fImage : file.listFiles()) {
//                    if (!fImage.isDirectory()) {
//                        // get image's name for naming model
//                        String iName = fImage.getName();
//                        System.out.println(iName);
//                        // load the model image
//                        BinaryImageShell tempImage = new BinaryImageShell(fImage);
//                        int [][] data = ArabicOCR.train(tempImage);
//                        finaldata = DataTrain.getMergeData(finaldata, data);
//                        
//                        HiddenMarkov hmm = new HiddenMarkov(data, Const.NUM_SYMBOLS);
//                        hmm.setTrainSeq(data);
//                        hmm.train();
//        //                        // write object
//                        Serialize.serializeModel(hmm, Const.MODELSVM_DATA + name + File.separator + iName + ".ser");
//                    }
//                }
//                
//            }
//        }
//    }
//}
