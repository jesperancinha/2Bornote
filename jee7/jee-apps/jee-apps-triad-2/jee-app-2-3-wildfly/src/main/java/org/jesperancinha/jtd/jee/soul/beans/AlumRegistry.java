package org.jesperancinha.jtd.jee.soul.beans;

import org.jesperancinha.console.consolerizer.Consolerizer;
import org.jesperancinha.jtd.jee.soul.domain.Album;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;

@RequestScoped
@Named("albumRegistry")
public class AlumRegistry {

    private Album newAlbum;

    public AlumRegistry(){
        this.newAlbum = new Album();
        this.newAlbum.setAlbumName("Unexpected");
        this.newAlbum.setPublishingDate(new Date(2009-1900, Calendar.NOVEMBER, 23));
    }

    public Album getNewAlbum() {
        return newAlbum;
    }

    public void setNewAlbum(Album newAlbum) {
        this.newAlbum = newAlbum;
    }

    public void register(){
        Consolerizer.printRandomColorGenericLn("%s has been registered!", newAlbum);
        Consolerizer.printGreenGenericLn("Well sort of");
        Consolerizer.printGreenGenericLn("This is just to simulate and see what happens between the browser and the server");
        Consolerizer.printGreenGenericLn("Note that formats much match.");
    }
}
