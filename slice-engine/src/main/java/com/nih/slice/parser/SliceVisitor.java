// Generated from Slice.g4 by ANTLR 4.13.0
package com.nih.slice.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SliceParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SliceVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SliceParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(SliceParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#instructions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstructions(SliceParser.InstructionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(SliceParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#simple_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_stmt(SliceParser.Simple_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#small_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmall_stmt(SliceParser.Small_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#expr_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_stmt(SliceParser.Expr_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SliceParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#par}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(SliceParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#set_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_stmt(SliceParser.Set_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(SliceParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#remove_from_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove_from_set(SliceParser.Remove_from_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#empty_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty_set(SliceParser.Empty_setContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#kill_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKill_stmt(SliceParser.Kill_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#rating_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRating_stmt(SliceParser.Rating_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#qualitative_rating}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualitative_rating(SliceParser.Qualitative_ratingContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#rating}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRating(SliceParser.RatingContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(SliceParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#exit_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExit_stmt(SliceParser.Exit_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#permute_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPermute_stmt(SliceParser.Permute_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#mech_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMech_stmt(SliceParser.Mech_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#ghost_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGhost_stmt(SliceParser.Ghost_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#import_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_stmt(SliceParser.Import_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#import_module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_module(SliceParser.Import_moduleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#import_from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_from(SliceParser.Import_fromContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#import_as_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_name(SliceParser.Import_as_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#import_as_names}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_as_names(SliceParser.Import_as_namesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#compound_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound_stmt(SliceParser.Compound_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(SliceParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest(SliceParser.TestContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#or_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_test(SliceParser.Or_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#and_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_test(SliceParser.And_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#test_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_block(SliceParser.Test_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#foreach_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeach_stmt(SliceParser.Foreach_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SliceParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(SliceParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(SliceParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#func_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_stmt(SliceParser.Func_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(SliceParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#fplist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFplist(SliceParser.FplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#fpdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFpdef(SliceParser.FpdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#test_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_stmt(SliceParser.Test_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#math_comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMath_comp_op(SliceParser.Math_comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(SliceParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#pos_relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPos_relation(SliceParser.Pos_relationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#neg_relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeg_relation(SliceParser.Neg_relationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SliceParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#term_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm_block(SliceParser.Term_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom1(SliceParser.Atom1Context ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom2(SliceParser.Atom2Context ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subjects}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubjects(SliceParser.SubjectsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#or_subject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_subject(SliceParser.Or_subjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#and_subject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_subject(SliceParser.And_subjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicates(SliceParser.PredicatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#or_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_predicate(SliceParser.Or_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#and_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_predicate(SliceParser.And_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_appendages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_appendages(SliceParser.Subject_appendagesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_appendages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_appendages(SliceParser.Predicate_appendagesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject(SliceParser.SubjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_atom(SliceParser.Subject_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_bond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_bond(SliceParser.Subject_bondContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_ring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_ring(SliceParser.Subject_ringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_molecule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_molecule(SliceParser.Subject_moleculeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_or_predicate_atom_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_or_predicate_atom_type(SliceParser.Subject_or_predicate_atom_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#subject_atom_locs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubject_atom_locs(SliceParser.Subject_atom_locsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(SliceParser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(SliceParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom(SliceParser.Predicate_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_bond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_bond(SliceParser.Predicate_bondContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_property(SliceParser.Predicate_atom_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_center_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_center_property(SliceParser.Predicate_atom_center_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_type_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_type_count(SliceParser.Predicate_atom_type_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_charge_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_charge_count(SliceParser.Predicate_charge_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_alkyl_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_alkyl_count(SliceParser.Predicate_alkyl_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_hetero_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_hetero_count(SliceParser.Predicate_hetero_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_bond_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_bond_property(SliceParser.Predicate_bond_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_or_bond_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_or_bond_property(SliceParser.Predicate_atom_or_bond_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_locs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_locs(SliceParser.Predicate_atom_locsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_atom_or_bond_or_group_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_atom_or_bond_or_group_property(SliceParser.Predicate_atom_or_bond_or_group_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_group_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_group_property(SliceParser.Predicate_group_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_group_property_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_group_property_count(SliceParser.Predicate_group_property_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_functional_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_functional_group(SliceParser.Predicate_functional_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_functional_group_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_functional_group_count(SliceParser.Predicate_functional_group_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_non_functional_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_non_functional_group(SliceParser.Predicate_non_functional_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_non_functional_group_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_non_functional_group_count(SliceParser.Predicate_non_functional_group_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_smarts_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_smarts_group(SliceParser.Predicate_smarts_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_smarts_group_count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_smarts_group_count(SliceParser.Predicate_smarts_group_countContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#predicate_ring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate_ring(SliceParser.Predicate_ringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#stereochemistry_predicate1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStereochemistry_predicate1(SliceParser.Stereochemistry_predicate1Context ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#stereochemistry_predicate2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStereochemistry_predicate2(SliceParser.Stereochemistry_predicate2Context ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#stereochemistry_predicate3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStereochemistry_predicate3(SliceParser.Stereochemistry_predicate3Context ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_stmt(SliceParser.Loop_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject(SliceParser.Loop_subjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_atom_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_atom_type(SliceParser.Loop_subject_atom_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_atom_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_atom_property(SliceParser.Loop_subject_atom_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_atom_combined}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_atom_combined(SliceParser.Loop_subject_atom_combinedContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_bond_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_bond_property(SliceParser.Loop_subject_bond_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_group_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_group_property(SliceParser.Loop_subject_group_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_functional_group_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_functional_group_property(SliceParser.Loop_subject_functional_group_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_non_functional_group_property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_non_functional_group_property(SliceParser.Loop_subject_non_functional_group_propertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_subject_atom_locs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_subject_atom_locs(SliceParser.Loop_subject_atom_locsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_predicate(SliceParser.Loop_predicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#loop_predicate_ring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop_predicate_ring(SliceParser.Loop_predicate_ringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#set_current_ring}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_current_ring(SliceParser.Set_current_ringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom_on_bond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_on_bond(SliceParser.Atom_on_bondContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#bond_on_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBond_on_atom(SliceParser.Bond_on_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(SliceParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#variable_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_name(SliceParser.Variable_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#defined_as}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefined_as(SliceParser.Defined_asContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#where_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_stmt(SliceParser.Where_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#ring_prefix_prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRing_prefix_prop(SliceParser.Ring_prefix_propContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#ring_suffix_prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRing_suffix_prop(SliceParser.Ring_suffix_propContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#ring_size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRing_size(SliceParser.Ring_sizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#alpha_num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlpha_num(SliceParser.Alpha_numContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#numeric_num}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_num(SliceParser.Numeric_numContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atomtype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomtype(SliceParser.AtomtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#functional_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctional_group(SliceParser.Functional_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#non_functional_group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_functional_group(SliceParser.Non_functional_groupContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#smarts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmarts(SliceParser.SmartsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom_loc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_loc(SliceParser.Atom_locContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom_properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_properties(SliceParser.Atom_propertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom_or_bond_properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_or_bond_properties(SliceParser.Atom_or_bond_propertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#bond_properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBond_properties(SliceParser.Bond_propertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#atom_or_bond_or_group_properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_or_bond_or_group_properties(SliceParser.Atom_or_bond_or_group_propertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#group_properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup_properties(SliceParser.Group_propertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#charge_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharge_stmt(SliceParser.Charge_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#centre_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCentre_stmt(SliceParser.Centre_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#ring_prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRing_prop(SliceParser.Ring_propContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#alkyl_number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlkyl_number(SliceParser.Alkyl_numberContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(SliceParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#notcontain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotcontain(SliceParser.NotcontainContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#contain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContain(SliceParser.ContainContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#notequal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotequal(SliceParser.NotequalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(SliceParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#same}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSame(SliceParser.SameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(SliceParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(SliceParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by {@link SliceParser#smarts_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSmarts_char(SliceParser.Smarts_charContext ctx);
}