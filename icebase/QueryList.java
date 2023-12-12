package icebase.icebase;

import java.io.IOException;
import java.util.ArrayList;

public class QueryList extends ArrayList<Doc> {

    public QueryList where(String query, int fieldIndex) throws IOException {
        QueryList docs = this;
        QueryList filteredDocs = new QueryList();

        for (Doc doc : docs) {
            String[] dataFields = doc.data().split(",");

            if (fieldIndex >= dataFields.length) {
                continue;
            }

            if (dataFields[fieldIndex].trim().equalsIgnoreCase(query)) {
                filteredDocs.add(doc);
            }
        }

        return filteredDocs;
    }
}
