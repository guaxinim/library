package com.guaxinim.library.setup;

import com.guaxinim.library.api.ActionRest;
import com.guaxinim.library.domain.Action;
import com.guaxinim.library.domain.Object;
import com.guaxinim.library.domain.Person;
import com.guaxinim.library.setup.neo4j.Neo4jDriver;
import com.guaxinim.library.setup.neo4j.Neo4jSession;
import com.guaxinim.library.setup.neo4j.Neo4jSessionFactory;
import junit.framework.Assert;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

//@RunWith(Arquillian.class)
public class ActionRestTest {

    Logger log = Logger.getLogger(ActionRestTest.class.getName());

    //@Deployment
    public static WebArchive createTestArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(Setup.class)
                .addClass(Neo4jDriver.class)
                .addClass(Neo4jSession.class)
                .addClass(Neo4jSessionFactory.class)
                .addPackage("com.guaxinim.library.domain")
                .addPackage("com.guaxinim.library.api")
                .addPackage("com.guaxinim.library.persistence")
                .addPackage("com.guaxinim.library.service")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/jboss-deployment-structure.xml.rej"))
                .addAsResource(new File("src/main/resources/settings.properties"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    //@Test
    //@InSequence(5)
    public void insertAction(@ArquillianResteasyResource ActionRest actionResource) {
        log.info("[TESTS] - POSTING new Action...");
        Action action = new Action();
        action.setName("run");
        action.setWhen(new Date());
        action.setDidBy(new HashSet<Person>());
        action.setToSmb(new HashSet<Person>());
        action.setToSmt(new HashSet<Object>());
        action.setWithSmb(new HashSet<Person>());
        action.setWithSmt(new HashSet<Object>());
        Response response = actionResource.insertAction(action);
        Assert.assertTrue(response.getStatus() == 1);
    }

    //@Test
    //@InSequence(6)
    public void getAction(@ArquillianResteasyResource ActionRest actionResource) {
        log.info("[TESTS] - Getting actions ...");
        List<Action> actions = actionResource.getActions();
        Assert.assertFalse(actions.isEmpty());
    }

}
