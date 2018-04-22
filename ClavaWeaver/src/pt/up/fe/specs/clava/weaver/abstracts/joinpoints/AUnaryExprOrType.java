package pt.up.fe.specs.clava.weaver.abstracts.joinpoints;

import org.lara.interpreter.weaver.interf.events.Stage;
import java.util.Optional;
import org.lara.interpreter.exception.AttributeException;
import org.lara.interpreter.exception.ActionException;
import java.util.List;
import java.util.Map;
import org.lara.interpreter.weaver.interf.JoinPoint;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * Auto-Generated class for join point AUnaryExprOrType
 * This class is overwritten by the Weaver Generator.
 * 
 * 
 * @author Lara Weaver Generator
 */
public abstract class AUnaryExprOrType extends AExpression {

    protected AExpression aExpression;

    /**
     * 
     */
    public AUnaryExprOrType(AExpression aExpression){
        this.aExpression = aExpression;
    }
    /**
     * Get value on attribute kind
     * @return the attribute's value
     */
    public abstract String getKindImpl();

    /**
     * Get value on attribute kind
     * @return the attribute's value
     */
    public final Object getKind() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "kind", Optional.empty());
        	}
        	String result = this.getKindImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "kind", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "kind", e);
        }
    }

    /**
     * Get value on attribute hasTypeExpr
     * @return the attribute's value
     */
    public abstract Boolean getHasTypeExprImpl();

    /**
     * Get value on attribute hasTypeExpr
     * @return the attribute's value
     */
    public final Object getHasTypeExpr() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "hasTypeExpr", Optional.empty());
        	}
        	Boolean result = this.getHasTypeExprImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "hasTypeExpr", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "hasTypeExpr", e);
        }
    }

    /**
     * Get value on attribute hasArgExpr
     * @return the attribute's value
     */
    public abstract Boolean getHasArgExprImpl();

    /**
     * Get value on attribute hasArgExpr
     * @return the attribute's value
     */
    public final Object getHasArgExpr() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "hasArgExpr", Optional.empty());
        	}
        	Boolean result = this.getHasArgExprImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "hasArgExpr", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "hasArgExpr", e);
        }
    }

    /**
     * Get value on attribute argType
     * @return the attribute's value
     */
    public abstract AType getArgTypeImpl();

    /**
     * Get value on attribute argType
     * @return the attribute's value
     */
    public final Object getArgType() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "argType", Optional.empty());
        	}
        	AType result = this.getArgTypeImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "argType", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "argType", e);
        }
    }

    /**
     * 
     */
    public void defArgTypeImpl(AType value) {
        throw new UnsupportedOperationException("Join point "+get_class()+": Action def argType with type AType not implemented ");
    }

    /**
     * Get value on attribute argExpr
     * @return the attribute's value
     */
    public abstract AExpression getArgExprImpl();

    /**
     * Get value on attribute argExpr
     * @return the attribute's value
     */
    public final Object getArgExpr() {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.BEGIN, this, "argExpr", Optional.empty());
        	}
        	AExpression result = this.getArgExprImpl();
        	if(hasListeners()) {
        		eventTrigger().triggerAttribute(Stage.END, this, "argExpr", Optional.ofNullable(result));
        	}
        	return result!=null?result:getUndefinedValue();
        } catch(Exception e) {
        	throw new AttributeException(get_class(), "argExpr", e);
        }
    }

    /**
     * 
     * @param argType 
     */
    public void setArgTypeImpl(AType argType) {
        throw new UnsupportedOperationException(get_class()+": Action setArgType not implemented ");
    }

    /**
     * 
     * @param argType 
     */
    public final void setArgType(AType argType) {
        try {
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.BEGIN, "setArgType", this, Optional.empty(), argType);
        	}
        	this.setArgTypeImpl(argType);
        	if(hasListeners()) {
        		eventTrigger().triggerAction(Stage.END, "setArgType", this, Optional.empty(), argType);
        	}
        } catch(Exception e) {
        	throw new ActionException(get_class(), "setArgType", e);
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
     */
    @Override
    public AJoinPoint copyImpl() {
        return this.aExpression.copyImpl();
    }

    /**
     * 
     * @param fieldName 
     * @param value 
     */
    @Override
    public Object setUserFieldImpl(String fieldName, Object value) {
        return this.aExpression.setUserFieldImpl(fieldName, value);
    }

    /**
     * 
     * @param fieldNameAndValue 
     */
    @Override
    public Object setUserFieldImpl(Map<?, ?> fieldNameAndValue) {
        return this.aExpression.setUserFieldImpl(fieldNameAndValue);
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
    public final void defImpl(String attribute, Object value) {
        switch(attribute){
        case "type": {
        	if(value instanceof AJoinPoint){
        		this.defTypeImpl((AJoinPoint)value);
        		return;
        	}
        	this.unsupportedTypeForDef(attribute, value);
        }
        case "argType": {
        	if(value instanceof AType){
        		this.defArgTypeImpl((AType)value);
        		return;
        	}
        	this.unsupportedTypeForDef(attribute, value);
        }
        default: throw new UnsupportedOperationException("Join point "+get_class()+": attribute '"+attribute+"' cannot be defined");
        }
    }

    /**
     * 
     */
    @Override
    protected final void fillWithAttributes(List<String> attributes) {
        this.aExpression.fillWithAttributes(attributes);
        attributes.add("kind");
        attributes.add("hasTypeExpr");
        attributes.add("hasArgExpr");
        attributes.add("argType");
        attributes.add("argExpr");
    }

    /**
     * 
     */
    @Override
    protected final void fillWithSelects(List<String> selects) {
        this.aExpression.fillWithSelects(selects);
    }

    /**
     * 
     */
    @Override
    protected final void fillWithActions(List<String> actions) {
        this.aExpression.fillWithActions(actions);
        actions.add("void setArgType(type)");
    }

    /**
     * Returns the join point type of this class
     * @return The join point type
     */
    @Override
    public final String get_class() {
        return "unaryExprOrType";
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
    protected enum UnaryExprOrTypeAttributes {
        KIND("kind"),
        HASTYPEEXPR("hasTypeExpr"),
        HASARGEXPR("hasArgExpr"),
        ARGTYPE("argType"),
        ARGEXPR("argExpr"),
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
        DESCENDANTSANDSELF("descendantsAndSelf"),
        ASTNUMCHILDREN("astNumChildren"),
        TYPE("type"),
        DESCENDANTS("descendants"),
        ASTCHILDREN("astChildren"),
        ROOT("root"),
        JAVAVALUE("javaValue"),
        CHAINANCESTOR("chainAncestor"),
        CHAIN("chain"),
        JOINPOINTTYPE("joinpointType"),
        CURRENTREGION("currentRegion"),
        ANCESTOR("ancestor"),
        HASASTPARENT("hasAstParent"),
        ASTCHILD("astChild"),
        PARENTREGION("parentRegion"),
        ASTNAME("astName"),
        ASTID("astId"),
        CONTAINS("contains"),
        ASTISINSTANCE("astIsInstance"),
        JAVAFIELDS("javaFields"),
        ASTPARENT("astParent"),
        JAVAFIELDTYPE("javaFieldType"),
        USERFIELD("userField"),
        LOCATION("location"),
        HASNODE("hasNode"),
        GETUSERFIELD("getUserField"),
        HASPARENT("hasParent");
        private String name;

        /**
         * 
         */
        private UnaryExprOrTypeAttributes(String name){
            this.name = name;
        }
        /**
         * Return an attribute enumeration item from a given attribute name
         */
        public static Optional<UnaryExprOrTypeAttributes> fromString(String name) {
            return Arrays.asList(values()).stream().filter(attr -> attr.name.equals(name)).findAny();
        }

        /**
         * Return a list of attributes in String format
         */
        public static List<String> getNames() {
            return Arrays.asList(values()).stream().map(UnaryExprOrTypeAttributes::name).collect(Collectors.toList());
        }

        /**
         * True if the enum contains the given attribute name, false otherwise.
         */
        public static boolean contains(String name) {
            return fromString(name).isPresent();
        }
    }
}
