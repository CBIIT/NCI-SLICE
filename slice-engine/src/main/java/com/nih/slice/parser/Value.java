package com.nih.slice.parser;

import java.util.List;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.isomorphism.matchers.Expr;
import org.openscience.cdk.isomorphism.matchers.Expr.Type;

import com.nih.slice.config.SliceAtom.atomProp;
import com.nih.slice.config.SliceAtom.atomType;
import com.nih.slice.config.SliceBond.bondProp;
import com.nih.slice.config.SliceGroup.ChemSmarts;
import com.nih.slice.config.SliceGroup.FunctionalGroup;
import com.nih.slice.config.SliceGroup.groupProp;
import com.nih.slice.config.SliceType.Charge;

public class Value {
	public static Value VOID = new Value(new Object());

	private Object value;
	private int number = -1;
	private Type type;
	private Value left, right;

	public Value(Object value) {
		this.value = value;
	}
	
	public Value(Object value, int number) {
		this.value = value;
		this.number = number;
	}
	
	public Value(Type type, Value left, Value right) {
		this.type = type;
        this.left = left;
        this.right = right;
	}

	public Boolean asBoolean() {
		return (Boolean) value;
	}

	public Double asDouble() {
		return (Double) value;
	}
	
	public Integer asInteger() {
		return (Integer) value;
	}

	public String asString() {
		return String.valueOf(value);
	}

	public IChemObject asIChemObject() {
		return (IChemObject) value;
	}
	
	public atomProp asAtomProp() {
		return (atomProp) value;
	}
	
	public atomType asAtomType() {
		return (atomType) value;
	}
	
	public bondProp asBondProp() {
		return (bondProp) value;
	}
	
	public FunctionalGroup asFuntionalGroup() {
		return (FunctionalGroup) value;
	}
	
	public groupProp asGroupProp() {
		return (groupProp) value;
	}
	
	public Charge asCharge() {
		return (Charge) value;
	}
	
	public ChemSmarts asChemSmarts() {
		return (ChemSmarts) value;
	}
	
	public Value[] asArray() {
		return (Value[]) value;
	}
	
	public Value[][] asDoubleArray() {
		return (Value[][]) value;
	}
	
	public List<IChemObject> asIChemObjectList() {
		return (List<IChemObject>) value;
	}
	
	public List<Object> asObjectList() {
		return (List<Object>) value;
	}
	
	public boolean isBoolean() {
		return value instanceof Boolean;
	}
	
	public boolean isInt() {
		return value instanceof Integer;
	}
	
	public boolean isDouble() {
		return value instanceof Double || value instanceof Integer;
	}
	
	public boolean isString() {
		return value instanceof String;
	}
	
	public boolean isIChemObject() {
		return value instanceof IChemObject;
	}
	
	public boolean isIAtomContainer() {
		return value instanceof IAtomContainer;
	}
	
	public boolean isAtomProp() {
		return value instanceof atomProp;
	}
	
	public boolean isAtomType() {
		return value instanceof atomType;
	}
	
	public boolean isBondProp() {
		return value instanceof bondProp;
	}
	
	public boolean isFuntionalGroup() {
		return value instanceof FunctionalGroup;
	}
	
	public boolean isGroupProp() {
		return value instanceof groupProp;
	}
	
	public boolean isCharge() {
		return value instanceof Charge;
	}
	
	public boolean isChemSmarts() {
		return value instanceof ChemSmarts;
	}
	
	public boolean isArray() {
		return value instanceof Value[];
	}
	
	public boolean isDoubleArray() {
		return value instanceof Value[][];
	}
	
	public boolean isList() {
		return value instanceof List<?>;
	}
	
    public Type type() {
        return type;
    }
    
    public Value left() {
        return left;
    }

    public Value right() {
        return right;
    }
	
	@Override
	public int hashCode() {

		if (value == null) {
			return 0;
		}

		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		if (value == o) {
			return true;
		}

		if (value == null || o == null || o.getClass() != value.getClass()) {
			return false;
		}

		Value that = (Value) o;

		return this.value.equals(that.value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
    public enum Type {
    	OR,
    	AND,
    	NONE;
    }
}
