package com.company;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public static void main(String[] args)
	
	public class Extension{
		
	
	{ static Map<String,Integer> countObj=new LinkedHashMap<>();
      static Map<String,Long> countSize=new LinkedHashMap<>();
	  
		Extension()
		{
			
	    countObj.put("jpg",0);
        countObj.put("png",0);
        countObj.put("gif",0);
        countObj.put("mp4",0);
        countObj.put("mp3",0);
        countObj.put("exe",0);
        countObj.put("psd",0);
        countObj.put("html",0);
        countObj.put("xml",0);
        
        countSize.put("jpg",new Long(0));
        countSize.put("png",new Long(0));
        countSize.put("gif",new Long(0));
        countSize.put("mp4",new Long(0));
        countSize.put("mp3",new Long(0));
        countSize.put("exe",new Long(0));
        countSize.put("psd",new Long(0));
        countSize.put("html",new Long(0));
        countSize.put("xml",new Long(0));
		
		}
		
    public static void main(String[] args) throws InterruptedException{
	
        long startTime=System.currentTimeMillis();
        Path directoryPath= null;
        try {
             directoryPath= Paths.get(args[0]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.print("Enter vaklid path");
        }

        
       
        FileCounter countOfFiles[]=new FileCounter[9];

        countOfFiles[0] = new FileCounter(directoryPath,"jpg");
        countOfFiles[1] = new FileCounter(directoryPath,"png");
        countOfFiles[2] = new FileCounter(directoryPath,"gif");
        countOfFiles[3] = new FileCounter(directoryPath,"mp3");
        countOfFiles[4] = new FileCounter(directoryPath,"mp4");
        countOfFiles[5] = new FileCounter(directoryPath,"exe");
        countOfFiles[6] = new FileCounter(directoryPath,"psd");
        countOfFiles[7] = new FileCounter(directoryPath,"html");
        countOfFiles[8] = new FileCounter(directoryPath,"xml");
        
        countOfFiles[0].start();
        countOfFiles[1].start();
        countOfFiles[2].start();
        countOfFiles[3].start();
        countOfFiles[4].start();
        countOfFiles[5].start();
        countOfFiles[6].start();
        countOfFiles[7].start();
        countOfFiles[8].start();

        
        countOfFiles[0].join();
        countOfFiles[1].join();
        countOfFiles[2].join();
        countOfFiles[3].join();
        countOfFiles[4].join();
        countOfFiles[5].join();
        countOfFiles[6].join();
        countOfFiles[8].join();


        
        System.out.println( "Count of files : " +
                countObj.entrySet()
                        .stream().parallel()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))
        );
        
        System.out.println("Size of files : " +
            countSize.entrySet()
                .stream().parallel()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ))
        );

        
        long endTime=System.currentTimeMillis();
        System.out.println("Time staken to execute : "+(endTime-startTime));
    }

    
    synchronized void incrementingVariable(String index){
        
        countObj.put(index,new Integer(countObj.get(index)+1));
    }

    
    synchronized void additionOfSize(String index,long size){
        countSize.put(index,new Long(countSize.get(index)+size));
    }
}

	}
	
	public class FileCounter extends java.lang.Thread {
     Path directoryPath;
     String fileType;
	
     
     FileCounter(Path directoryPath,String fileType){
        this.directoryPath= directoryPath;
        this.fileType = fileType;

    }
     private Main accessOfVariable=new Main();

     
    @Override public void run(){

        PathMatcher matcher;
		matcher=FileSystems.getDefault().getPathMatcher("glob:*."+fileType+"");
        occurrenceCount(directoryPath,matcher,fileType);


    }
     
    private void occurrenceCount(Path pathToDirectory, PathMatcher matcher, String typeOfFile){ 
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(pathToDirectory)){
            for(Path path:stream){

                if(Files.isDirectory(path))
                {
                    occurrenceCount(path,matcher,typeOfFile);
                   
                }
                else {

                    if(matcher.matches(path.getFileName()))
                    {
                        accessOfVariable.incrementingVariable(typeOfFile);
                        accessOfVariable.additionOfSize(typeOfFile,Files.size(path));
                       System.out.println(path.getFileName());
                    }
                }


            }
        }catch (IOException | DirectoryIteratorException e){
            
        }
    }
	
	}
	
	
				