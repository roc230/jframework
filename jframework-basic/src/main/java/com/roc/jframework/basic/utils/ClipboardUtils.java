package com.roc.jframework.basic.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * 粘贴板操作类
 */
public class ClipboardUtils {

    public static void setSysClipboardTxt(String txt){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable text = new StringSelection(txt);
        clipboard.setContents(text, null);
    }
}
