package edu.wright.dirsyncpro;

//enum FileType{
//   char fileType;
//
//   FileType(char fileType){
//      this.fileType = fileType;
//   }
//
//    FILE('f'),
//    DIRECTORY('d');
//}

/*
devices
   id
   name

dirs
   deviceId
   parentDirId
   id
   name
   path

files
   dirId

 */

public class FileRecord {
   int id; //?
   int storageId;
   int pathId;
   String fileName;
   String path;
  // FileType fileType;
   String hash;
   long size;

}
