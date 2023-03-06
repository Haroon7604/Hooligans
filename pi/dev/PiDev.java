package pi.dev;

import models.Commentaire;
import models.Publication;
import Service.commentaireService;
import Service.publicationService;
import java.sql.Connection;
import utile.MyConnection;

/**
 *
 * @author azizh
 */
public class PiDev {
    
 

    public static void main(String[] args) {
        //commentaireService commentaireService =new commentaireService();
        //publicationService PublicationService = new publicationService();
        Connection cnx = MyConnection.getInstance().getCnx();
        Publication P = new Publication(16,"lkfjldk",4,4,4);
        publicationService s = new publicationService(cnx);
        commentaireService c = new commentaireService(cnx);
       

       //s.update(1, "lkjhkjhkjhkhj");
       // c.update(1, "jdida", 1, 12);
        //s.displayPublications();
        //c.displayComments(1);
        //s.create(12, "444ddd", 2,1,1);
        //s.update(12, "mouhib", 122);
        //s.delete(12);
          //s.update( 122, " update jasser",3);
        //s.getPublicationById(12);
        //s.delete(1);
        //c.create(1, "description", 5, 1);
        //c.update(1, 0, "mlkmklj");
        //c.delete(1, 0);
       // c.displayComments(1);
        //s.create(1, "hello mongi", 0, 0, 1);
        //s.addLike(1);
        
        
    }
}
