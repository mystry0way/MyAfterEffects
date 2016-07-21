
package controller;

import app.MyAfterEffectsApp;
import de.jaret.util.date.JaretDate;
import de.jaret.util.misc.Pair;
import framework.Application;
import framework.Controller;
import manager.Media;
import model.MenuModel;
import model.TimelineModel;
import view.MenuView;
import view.TimelineView;

import java.io.IOException;
import java.util.ArrayList;

public final class MenuController extends Controller<MenuModel, MenuView> {

    public MenuController(final Application application) {
        super(application);
    }

    public void handle(String name) {
        switch (name) {
            case "Exit":
                this.model().exit(0);
                break;
            case "New":
                try {
                    this.model().newFile();
                } catch (IllegalArgumentException | IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "Export":
                this.model().exportVideo();
                break;
            case "Render":
                System.out.println(TimelineModel.getTimelineEnd().toDisplayString());
        /* TODO Adrien */
                ArrayList<Pair<Long, Media>> medias = TimelineModel.getMediasAtDate(TimelineModel.getMarkerTime());
                for (Pair<Long, Media> p : medias)
                    System.out.println(p.getLeft() + " " + p.getRight().getName());

        /* To change the marker */
                MyAfterEffectsApp app = ((MyAfterEffectsApp) this.application());
                app.getTimelineView().setMarkerTime(new JaretDate(0, 0, 0, 0, 0, 5));
                break;
        }
    }
}
