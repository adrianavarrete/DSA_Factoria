import java.util.HashMap;
import java.util.logging.Logger;

public class CommandFactory {

    final static Logger log = Logger.getLogger(CommandFactory.class.getName());

    private static CommandFactory instance;

    private HashMap<String, Command> cache;

    public CommandFactory(){

        this.cache = new HashMap<String, Command>();
    }

    public static CommandFactory getInstance(){
        if( instance == null){
            instance = new CommandFactory();

        }
        return instance;
    }

    public Command getCommand(String id){

        Command command = this.cache.get(id);

        if (command == null){
            command = getNewCommand(id);
            this.cache.put(id,command);
        }

        return command;
    }

    public Command getNewCommand(String name){

        Command command = null;
        Class a = null;

        try{
            a = Class.forName(name);
            command = (Command) a.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return command;
    }
}
