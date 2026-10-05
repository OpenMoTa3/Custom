package org.dev.custom.provider;

import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.os.CancellationSignal;
import java.io.FileNotFoundException;
import android.provider.DocumentsProvider;

public class DataProvider extends DocumentsProvider {
    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public ParcelFileDescriptor openDocument(String arg0, String arg1, CancellationSignal arg2)
            throws FileNotFoundException {
        return null;
    }

    @Override
    public Cursor queryChildDocuments(String arg0, String[] arg1, String arg2)
            throws FileNotFoundException {
        return null;
    }

    @Override
    public Cursor queryDocument(String arg0, String[] arg1) throws FileNotFoundException {
        return null;
    }

    @Override
    public Cursor queryRoots(String[] arg0) throws FileNotFoundException {

        return null;
    }
}
