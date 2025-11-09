package edu.wright.dirsyncpro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstTest {
   @Test
   void print_SyncMode()
   {
      assertEquals("ABMirror", Const.SyncMode.ABMirror.name());
      assertEquals("Mirror A -> B (incremental)", Const.SyncMode.ABMirror.toString());
   }

}