package templatesTutorial;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.List;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PrintInvitations {
    public static void main(String[] args) throws Exception {
        XMLDecoder decoder = new XMLDecoder(new FileInputStream("friends.xml"));
        List<Person> friends = (List<Person>) decoder.readObject();
        decoder.close();

        Velocity.init();

        PrintWriter out = new PrintWriter(new FileWriter("invitations.txt"));

        for (Person p : friends) {
            VelocityContext context = new VelocityContext();
            context.put("person", p);

            StringWriter sw = new StringWriter();
            Velocity.mergeTemplate("invitation.vm", "UTF-8", context, sw);
            out.write(sw.toString());
            out.write("\n\n");
        }

        out.close();
        System.out.println("invitations.txt generated.");
    }
}
