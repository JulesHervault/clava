package pt.up.fe.specs.clava.weaver.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.AttributeException;
import javax.script.Bindings;
import java.util.List;
import org.lara.interpreter.exception.ActionException;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point ACall
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class ACall extends AExpression {

    private AExpression aExpression;

    /**
     * 
     */
    public ACall(AExpression aExpression){
        this.aExpression = aExpression;
    }
    /**
     * Get value on attribute name
     * @return the attribute's value
     */
    public abstract String getNameImpl();

    /**
     * Get value on attribute name
     * @return the attribute's value
     */
    public final Object getName() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "name", Optional.empty());
        	}
        	String result = this.getNameImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "name", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "name", e);
        }
    }

    /**
     * Get value on attribute numArgs
     * @return the attribute's value
     */
    public abstract Integer getNumArgsImpl();

    /**
     * Get value on attribute numArgs
     * @return the attribute's value
     */
    public final Object getNumArgs() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "numArgs", Optional.empty());
        	}
        	Integer result = this.getNumArgsImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "numArgs", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "numArgs", e);
        }
    }

    /**
     * Get value on attribute memberNames
     * @return the attribute's value
     */
    public abstract String[] getMemberNamesArrayImpl();

    /**
     * Get value on attribute memberNames
     * @return the attribute's value
     */
    public Bindings getMemberNamesImpl() {
        String[] stringArrayImpl0 = getMemberNamesArrayImpl();
        Bindings nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(stringArrayImpl0);
        return nativeArray0;
    }

    /**
     * Get value on attribute memberNames
     * @return the attribute's value
     */
    public final Object getMemberNames() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "memberNames", Optional.empty());
        	}
        	Bindings result = this.getMemberNamesImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "memberNames", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "memberNames", e);
        }
    }

    /**
     * a 'function' join point that represents the function declaration of the call; 'undefined' if no declaration was found
     */
    public abstract AJoinPoint getDeclarationImpl();

    /**
     * a 'function' join point that represents the function declaration of the call; 'undefined' if no declaration was found
     */
    public final Object getDeclaration() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "declaration", Optional.empty());
        	}
        	AJoinPoint result = this.getDeclarationImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "declaration", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "declaration", e);
        }
    }

    /**
     * a 'function' join point that represents the function definition of the call; 'undefined' if no definition was found
     */
    public abstract AJoinPoint getDefinitionImpl();

    /**
     * a 'function' join point that represents the function definition of the call; 'undefined' if no definition was found
     */
    public final Object getDefinition() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "definition", Optional.empty());
        	}
        	AJoinPoint result = this.getDefinitionImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "definition", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "definition", e);
        }
    }

    /**
     * Get value on attribute argList
     * @return the attribute's value
     */
    public abstract String[] getArgListArrayImpl();

    /**
     * Get value on attribute argList
     * @return the attribute's value
     */
    public Bindings getArgListImpl() {
        String[] stringArrayImpl0 = getArgListArrayImpl();
        Bindings nativeArray0 = getWeaverEngine().getScriptEngine().toNativeArray(stringArrayImpl0);
        return nativeArray0;
    }

    /**
     * Get value on attribute argList
     * @return the attribute's value
     */
    public final Object getArgList() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "argList", Optional.empty());
        	}
        	Bindings result = this.getArgListImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "argList", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "argList", e);
        }
    }

    /**
     * Get value on attribute returnType
     * @return the attribute's value
     */
    public abstract AJoinPoint getReturnTypeImpl();

    /**
     * Get value on attribute returnType
     * @return the attribute's value
     */
    public final Object getReturnType() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "returnType", Optional.empty());
        	}
        	AJoinPoint result = this.getReturnTypeImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "returnType", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "returnType", e);
        }
    }

    /**
     * Method used by the lara interpreter to select callees
     * @return 
     */
    public abstract List<? extends AExpression> selectCallee();

    /**
     * Method used by the lara interpreter to select args
     * @return 
     */
    public abstract List<? extends AExpression> selectArg();

    /**
     * 
     * @param name 
     */
    public void setNameImpl(String name) {
        throw new UnsupportedOperationException(get_class()+": Action setName not implemented ");
    }

    /**
     * 
     * @param name 
     */
    public final void setName(String name) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "setName", this, Optional.empty(), name);
        	}
        	this.setNameImpl(name);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "setName", this, Optional.empty(), name);
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "setName", e);
        }
    }

    /**
     * Wraps this call with a possibly new wrapping function
     * @param name 
     */
    public void wrapImpl(String name) {
        throw new UnsupportedOperationException(get_class()+": Action wrap not implemented ");
    }

    /**
     * Wraps this call with a possibly new wrapping function
     * @param name 
     */
    public final void wrap(String name) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "wrap", this, Optional.empty(), name);
        	}
        	this.wrapImpl(name);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "wrap", this, Optional.empty(), name);
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "wrap", e);
        }
    }

    /**
     * Get value on attribute vardecl
     * @return the attribute's value
     */
    @Override
    public AJoinPoint getVardeclImpl() {
        return this.aExpression.getVardeclImpl();
    }

    /**
     * Get value on attribute use
     * @return the attribute's value
     */
    @Override
    public String getUseImpl() {
        return this.aExpression.getUseImpl();
    }

    /**
     * Get value on attribute isFunctionArgument
     * @return the attribute's value
     */
    @Override
    public Boolean getIsFunctionArgumentImpl() {
        return this.aExpression.getIsFunctionArgumentImpl();
    }

    /**
     * Get value on attribute implicitCast
     * @return the attribute's value
     */
    @Override
    public ACast getImplicitCastImpl() {
        return this.aExpression.getImplicitCastImpl();
    }

    /**
     * Method used by the lara interpreter to select vardecls
     * @return 
     */
    @Override
    public List<? extends AVardecl> selectVardecl() {
        return this.aExpression.selectVardecl();
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint replaceWithImpl(AJoinPoint node) {
        return this.aExpression.replaceWithImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(AJoinPoint node) {
        return this.aExpression.insertBeforeImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertBeforeImpl(String node) {
        return this.aExpression.insertBeforeImpl(node);
    }

    /**
     * 
     * @param node 
     */
    @Override
    public AJoinPoint insertAfterImpl(AJoinPoint node) {
        return this.aExpression.insertAfterImpl(node);
    }

    /**
     * 
     * @param code 
     */
    @Override
    public AJoinPoint insertAfterImpl(String code) {
        return this.aExpression.insertAfterImpl(code);
    }

    /**
     * 
     */
    @Override
    public void detachImpl() {
        this.aExpression.detachImpl();
    }

    /**
     * 
     * @param type 
     */
    @Override
    public void setTypeImpl(AJoinPoint type) {
        this.aExpression.setTypeImpl(type);
    }

    /**
     * 
     * @param message 
     */
    @Override
    public void messageToUserImpl(String message) {
        this.aExpression.messageToUserImpl(message);
    }

    /**
     * 
     * @param position 
     * @param code 
     */
    @Override
    public void insertImpl(String position, String code) {
        this.aExpression.insertImpl(position, code);
    }

    /**
     * 
     * @param attribute 
     * @param value 
     */
    @Override
    public void defImpl(String attribute, Object value) {
        this.aExpression.defImpl(attribute, value);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return this.aExpression.toString();
    }

    /**
     * 
     */
    @Override
    public Optional<? extends AExpression> getSuper() {
        return Optional.of(this.aExpression);
    }

    /**
     * 
     */
    @Override
    public final List<? extends JoinPoint> select(String selectName) {
        List<? extends JoinPoint> joinPointList;
        switch(selectName) {
        	case "callee": 
        		joinPointList = selectCallee();
        		break;
        	case "arg": 
        		joinPointList = selectArg();
        		break;
        	case "vardecl": 
        		joinPointList = selectVardecl();
        		break;
        	default:
        		joinPointList = this.aExpression.select(selectName);
        		break;
        }
        return joinPointList;
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        this.aExpression.fillWithAttributes(attributes);
        attributes.add("name");
        attributes.add("numArgs");
        attributes.add("memberNames");
        attributes.add("declaration");
        attributes.add("definition");
        attributes.add("argList");
        attributes.add("returnType");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aExpression.fillWithSelects(selects);
        selects.add("callee");
        selects.add("arg");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aExpression.fillWithActions(actions);
        actions.add("void setName(string)");
        actions.add("void wrap(string)");
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "call";
    }

    /**
     * Defines if this joinpoint is an instanceof a given joinpoint class
     * @return True if this join point is an instanceof the given class
     */
    @Override
    public final boolean instanceOf(String joinpointClass) {
        boolean isInstance = get_class().equals(joinpointClass);
        if(isInstance) {
        	return true;
        }
        return this.aExpression.instanceOf(joinpointClass);
    }
    /**
     * 
     */
    protected enum CallAttributes {
        NAME("name"),
        NUMARGS("numArgs"),
        MEMBERNAMES("memberNames"),
        DECLARATION("declaration"),
        DEFINITION("definition"),
        ARGLIST("argList"),
        RETURNTYPE("returnType"),
        VARDECL("vardecl"),
        USE("use"),
        ISFUNCTIONARGUMENT("isFunctionArgument"),
        IMPLICITCAST("implicitCast"),
        PARENT("parent"),
        ASTANCESTOR("astAncestor"),
        AST("ast"),
        CODE("code"),
        ISINSIDELOOPHEADER("isInsideLoopHeader"),
        LINE("line"),
        ASTNUMCHILDREN("astNumChildren"),
        TYPE("type"),
        ROOT("root"),
        JAVAVALUE("javaValue"),
        CHAINANCESTOR("chainAncestor"),
        CHAIN("chain"),
        JOINPOINTTYPE("joinpointType"),
        CURRENTREGION("currentRegion"),
        ANCESTOR("ancestor"),
        ASTCHILD("astChild"),
        PARENTREGION("parentRegion"),
        ASTNAME("astName"),
        ASTID("astId"),
        CONTAINS("contains"),
        JAVAFIELDS("javaFields"),
        ASTPARENT("astParent"),
        SETUSERFIELD("setUserField"),
        JAVAFIELDTYPE("javaFieldType"),
        LOCATION("location"),
        GETUSERFIELD("getUserField"),
        HASPARENT("hasParent");
        private String name;

        /**
         * 
         */
        private CallAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<CallAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(CallAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
