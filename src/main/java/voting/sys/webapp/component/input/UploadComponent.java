package voting.sys.webapp.component.input;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.InputStream;

@CssImport("./styles/dialog-styles.css")
public class UploadComponent extends HorizontalLayout {

    private MemoryBuffer buffer = new MemoryBuffer();

    private Upload dropDisabledUpload;

    public UploadComponent() {
        addClassName("right-center-align-input");
        add(new Span("*File:"));
        add(getFileBuffer());
    }

    public void clearBuffer() {
        buffer = new MemoryBuffer();
        dropDisabledUpload.setReceiver(buffer);
    }

    public Upload getUpload() {
        return dropDisabledUpload;
    }

    public String getFileName() {
        return buffer.getFileName();
    }

    public FileData getFileData() {
        return buffer.getFileData();
    }

    public InputStream getFileInputStream() {
        return buffer.getInputStream();
    }

    private Div getFileBuffer() {
        dropDisabledUpload = new Upload(buffer);
        dropDisabledUpload.setDropAllowed(false);
        dropDisabledUpload.addSucceededListener(succeededEvent -> Notification.show(buffer.getFileName()));
        dropDisabledUpload.addClassName("larger-text-field");
        dropDisabledUpload.setMaxFileSize(10_485_760);

        return new Div(dropDisabledUpload);
    }

}
