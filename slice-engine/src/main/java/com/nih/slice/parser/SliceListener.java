// Generated from Slice.g4 by ANTLR 4.13.0
package com.nih.slice.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SliceParser}.
 */
public interface SliceListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SliceParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(SliceParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(SliceParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(SliceParser.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(SliceParser.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(SliceParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(SliceParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt(SliceParser.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt(SliceParser.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSmall_stmt(SliceParser.Small_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSmall_stmt(SliceParser.Small_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(SliceParser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(SliceParser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SliceParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SliceParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#par}.
	 * @param ctx the parse tree
	 */
	void enterPar(SliceParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#par}.
	 * @param ctx the parse tree
	 */
	void exitPar(SliceParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#set_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSet_stmt(SliceParser.Set_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#set_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSet_stmt(SliceParser.Set_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(SliceParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(SliceParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#remove_from_set}.
	 * @param ctx the parse tree
	 */
	void enterRemove_from_set(SliceParser.Remove_from_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#remove_from_set}.
	 * @param ctx the parse tree
	 */
	void exitRemove_from_set(SliceParser.Remove_from_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#empty_set}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_set(SliceParser.Empty_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#empty_set}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_set(SliceParser.Empty_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#kill_stmt}.
	 * @param ctx the parse tree
	 */
	void enterKill_stmt(SliceParser.Kill_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#kill_stmt}.
	 * @param ctx the parse tree
	 */
	void exitKill_stmt(SliceParser.Kill_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#rating_stmt}.
	 * @param ctx the parse tree
	 */
	void enterRating_stmt(SliceParser.Rating_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#rating_stmt}.
	 * @param ctx the parse tree
	 */
	void exitRating_stmt(SliceParser.Rating_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#qualitative_rating}.
	 * @param ctx the parse tree
	 */
	void enterQualitative_rating(SliceParser.Qualitative_ratingContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#qualitative_rating}.
	 * @param ctx the parse tree
	 */
	void exitQualitative_rating(SliceParser.Qualitative_ratingContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#rating}.
	 * @param ctx the parse tree
	 */
	void enterRating(SliceParser.RatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#rating}.
	 * @param ctx the parse tree
	 */
	void exitRating(SliceParser.RatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(SliceParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(SliceParser.Return_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#exit_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExit_stmt(SliceParser.Exit_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#exit_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExit_stmt(SliceParser.Exit_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#permute_stmt}.
	 * @param ctx the parse tree
	 */
	void enterPermute_stmt(SliceParser.Permute_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#permute_stmt}.
	 * @param ctx the parse tree
	 */
	void exitPermute_stmt(SliceParser.Permute_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#mech_stmt}.
	 * @param ctx the parse tree
	 */
	void enterMech_stmt(SliceParser.Mech_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#mech_stmt}.
	 * @param ctx the parse tree
	 */
	void exitMech_stmt(SliceParser.Mech_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#ghost_stmt}.
	 * @param ctx the parse tree
	 */
	void enterGhost_stmt(SliceParser.Ghost_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#ghost_stmt}.
	 * @param ctx the parse tree
	 */
	void exitGhost_stmt(SliceParser.Ghost_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void enterImport_stmt(SliceParser.Import_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#import_stmt}.
	 * @param ctx the parse tree
	 */
	void exitImport_stmt(SliceParser.Import_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#import_module}.
	 * @param ctx the parse tree
	 */
	void enterImport_module(SliceParser.Import_moduleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#import_module}.
	 * @param ctx the parse tree
	 */
	void exitImport_module(SliceParser.Import_moduleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#import_from}.
	 * @param ctx the parse tree
	 */
	void enterImport_from(SliceParser.Import_fromContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#import_from}.
	 * @param ctx the parse tree
	 */
	void exitImport_from(SliceParser.Import_fromContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_name(SliceParser.Import_as_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#import_as_name}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_name(SliceParser.Import_as_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void enterImport_as_names(SliceParser.Import_as_namesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#import_as_names}.
	 * @param ctx the parse tree
	 */
	void exitImport_as_names(SliceParser.Import_as_namesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(SliceParser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(SliceParser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(SliceParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(SliceParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(SliceParser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(SliceParser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#or_test}.
	 * @param ctx the parse tree
	 */
	void enterOr_test(SliceParser.Or_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#or_test}.
	 * @param ctx the parse tree
	 */
	void exitOr_test(SliceParser.Or_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#and_test}.
	 * @param ctx the parse tree
	 */
	void enterAnd_test(SliceParser.And_testContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#and_test}.
	 * @param ctx the parse tree
	 */
	void exitAnd_test(SliceParser.And_testContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#test_block}.
	 * @param ctx the parse tree
	 */
	void enterTest_block(SliceParser.Test_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#test_block}.
	 * @param ctx the parse tree
	 */
	void exitTest_block(SliceParser.Test_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#foreach_stmt}.
	 * @param ctx the parse tree
	 */
	void enterForeach_stmt(SliceParser.Foreach_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#foreach_stmt}.
	 * @param ctx the parse tree
	 */
	void exitForeach_stmt(SliceParser.Foreach_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SliceParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SliceParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(SliceParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(SliceParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(SliceParser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(SliceParser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#func_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFunc_stmt(SliceParser.Func_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#func_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFunc_stmt(SliceParser.Func_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(SliceParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(SliceParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#fplist}.
	 * @param ctx the parse tree
	 */
	void enterFplist(SliceParser.FplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#fplist}.
	 * @param ctx the parse tree
	 */
	void exitFplist(SliceParser.FplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#fpdef}.
	 * @param ctx the parse tree
	 */
	void enterFpdef(SliceParser.FpdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#fpdef}.
	 * @param ctx the parse tree
	 */
	void exitFpdef(SliceParser.FpdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#test_stmt}.
	 * @param ctx the parse tree
	 */
	void enterTest_stmt(SliceParser.Test_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#test_stmt}.
	 * @param ctx the parse tree
	 */
	void exitTest_stmt(SliceParser.Test_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#math_comp_op}.
	 * @param ctx the parse tree
	 */
	void enterMath_comp_op(SliceParser.Math_comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#math_comp_op}.
	 * @param ctx the parse tree
	 */
	void exitMath_comp_op(SliceParser.Math_comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(SliceParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(SliceParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#pos_relation}.
	 * @param ctx the parse tree
	 */
	void enterPos_relation(SliceParser.Pos_relationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#pos_relation}.
	 * @param ctx the parse tree
	 */
	void exitPos_relation(SliceParser.Pos_relationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#neg_relation}.
	 * @param ctx the parse tree
	 */
	void enterNeg_relation(SliceParser.Neg_relationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#neg_relation}.
	 * @param ctx the parse tree
	 */
	void exitNeg_relation(SliceParser.Neg_relationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SliceParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SliceParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#term_block}.
	 * @param ctx the parse tree
	 */
	void enterTerm_block(SliceParser.Term_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#term_block}.
	 * @param ctx the parse tree
	 */
	void exitTerm_block(SliceParser.Term_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom1}.
	 * @param ctx the parse tree
	 */
	void enterAtom1(SliceParser.Atom1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom1}.
	 * @param ctx the parse tree
	 */
	void exitAtom1(SliceParser.Atom1Context ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom2}.
	 * @param ctx the parse tree
	 */
	void enterAtom2(SliceParser.Atom2Context ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom2}.
	 * @param ctx the parse tree
	 */
	void exitAtom2(SliceParser.Atom2Context ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subjects}.
	 * @param ctx the parse tree
	 */
	void enterSubjects(SliceParser.SubjectsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subjects}.
	 * @param ctx the parse tree
	 */
	void exitSubjects(SliceParser.SubjectsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#or_subject}.
	 * @param ctx the parse tree
	 */
	void enterOr_subject(SliceParser.Or_subjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#or_subject}.
	 * @param ctx the parse tree
	 */
	void exitOr_subject(SliceParser.Or_subjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#and_subject}.
	 * @param ctx the parse tree
	 */
	void enterAnd_subject(SliceParser.And_subjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#and_subject}.
	 * @param ctx the parse tree
	 */
	void exitAnd_subject(SliceParser.And_subjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicates}.
	 * @param ctx the parse tree
	 */
	void enterPredicates(SliceParser.PredicatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicates}.
	 * @param ctx the parse tree
	 */
	void exitPredicates(SliceParser.PredicatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#or_predicate}.
	 * @param ctx the parse tree
	 */
	void enterOr_predicate(SliceParser.Or_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#or_predicate}.
	 * @param ctx the parse tree
	 */
	void exitOr_predicate(SliceParser.Or_predicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#and_predicate}.
	 * @param ctx the parse tree
	 */
	void enterAnd_predicate(SliceParser.And_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#and_predicate}.
	 * @param ctx the parse tree
	 */
	void exitAnd_predicate(SliceParser.And_predicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_appendages}.
	 * @param ctx the parse tree
	 */
	void enterSubject_appendages(SliceParser.Subject_appendagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_appendages}.
	 * @param ctx the parse tree
	 */
	void exitSubject_appendages(SliceParser.Subject_appendagesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_appendages}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_appendages(SliceParser.Predicate_appendagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_appendages}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_appendages(SliceParser.Predicate_appendagesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject}.
	 * @param ctx the parse tree
	 */
	void enterSubject(SliceParser.SubjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject}.
	 * @param ctx the parse tree
	 */
	void exitSubject(SliceParser.SubjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_atom}.
	 * @param ctx the parse tree
	 */
	void enterSubject_atom(SliceParser.Subject_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_atom}.
	 * @param ctx the parse tree
	 */
	void exitSubject_atom(SliceParser.Subject_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_bond}.
	 * @param ctx the parse tree
	 */
	void enterSubject_bond(SliceParser.Subject_bondContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_bond}.
	 * @param ctx the parse tree
	 */
	void exitSubject_bond(SliceParser.Subject_bondContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_ring}.
	 * @param ctx the parse tree
	 */
	void enterSubject_ring(SliceParser.Subject_ringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_ring}.
	 * @param ctx the parse tree
	 */
	void exitSubject_ring(SliceParser.Subject_ringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_molecule}.
	 * @param ctx the parse tree
	 */
	void enterSubject_molecule(SliceParser.Subject_moleculeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_molecule}.
	 * @param ctx the parse tree
	 */
	void exitSubject_molecule(SliceParser.Subject_moleculeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_or_predicate_atom_type}.
	 * @param ctx the parse tree
	 */
	void enterSubject_or_predicate_atom_type(SliceParser.Subject_or_predicate_atom_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_or_predicate_atom_type}.
	 * @param ctx the parse tree
	 */
	void exitSubject_or_predicate_atom_type(SliceParser.Subject_or_predicate_atom_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#subject_atom_locs}.
	 * @param ctx the parse tree
	 */
	void enterSubject_atom_locs(SliceParser.Subject_atom_locsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#subject_atom_locs}.
	 * @param ctx the parse tree
	 */
	void exitSubject_atom_locs(SliceParser.Subject_atom_locsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterComp_op(SliceParser.Comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitComp_op(SliceParser.Comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(SliceParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(SliceParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom(SliceParser.Predicate_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom(SliceParser.Predicate_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_bond}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_bond(SliceParser.Predicate_bondContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_bond}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_bond(SliceParser.Predicate_bondContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_property(SliceParser.Predicate_atom_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_property(SliceParser.Predicate_atom_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_center_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_center_property(SliceParser.Predicate_atom_center_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_center_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_center_property(SliceParser.Predicate_atom_center_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_type_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_type_count(SliceParser.Predicate_atom_type_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_type_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_type_count(SliceParser.Predicate_atom_type_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_charge_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_charge_count(SliceParser.Predicate_charge_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_charge_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_charge_count(SliceParser.Predicate_charge_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_alkyl_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_alkyl_count(SliceParser.Predicate_alkyl_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_alkyl_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_alkyl_count(SliceParser.Predicate_alkyl_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_hetero_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_hetero_count(SliceParser.Predicate_hetero_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_hetero_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_hetero_count(SliceParser.Predicate_hetero_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_bond_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_bond_property(SliceParser.Predicate_bond_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_bond_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_bond_property(SliceParser.Predicate_bond_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_or_bond_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_or_bond_property(SliceParser.Predicate_atom_or_bond_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_or_bond_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_or_bond_property(SliceParser.Predicate_atom_or_bond_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_locs}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_locs(SliceParser.Predicate_atom_locsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_locs}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_locs(SliceParser.Predicate_atom_locsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_atom_or_bond_or_group_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_atom_or_bond_or_group_property(SliceParser.Predicate_atom_or_bond_or_group_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_atom_or_bond_or_group_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_atom_or_bond_or_group_property(SliceParser.Predicate_atom_or_bond_or_group_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_group_property}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_group_property(SliceParser.Predicate_group_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_group_property}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_group_property(SliceParser.Predicate_group_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_group_property_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_group_property_count(SliceParser.Predicate_group_property_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_group_property_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_group_property_count(SliceParser.Predicate_group_property_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_functional_group}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_functional_group(SliceParser.Predicate_functional_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_functional_group}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_functional_group(SliceParser.Predicate_functional_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_functional_group_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_functional_group_count(SliceParser.Predicate_functional_group_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_functional_group_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_functional_group_count(SliceParser.Predicate_functional_group_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_non_functional_group}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_non_functional_group(SliceParser.Predicate_non_functional_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_non_functional_group}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_non_functional_group(SliceParser.Predicate_non_functional_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_non_functional_group_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_non_functional_group_count(SliceParser.Predicate_non_functional_group_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_non_functional_group_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_non_functional_group_count(SliceParser.Predicate_non_functional_group_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_smarts_group}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_smarts_group(SliceParser.Predicate_smarts_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_smarts_group}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_smarts_group(SliceParser.Predicate_smarts_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_smarts_group_count}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_smarts_group_count(SliceParser.Predicate_smarts_group_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_smarts_group_count}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_smarts_group_count(SliceParser.Predicate_smarts_group_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#predicate_ring}.
	 * @param ctx the parse tree
	 */
	void enterPredicate_ring(SliceParser.Predicate_ringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#predicate_ring}.
	 * @param ctx the parse tree
	 */
	void exitPredicate_ring(SliceParser.Predicate_ringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#stereochemistry_predicate1}.
	 * @param ctx the parse tree
	 */
	void enterStereochemistry_predicate1(SliceParser.Stereochemistry_predicate1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#stereochemistry_predicate1}.
	 * @param ctx the parse tree
	 */
	void exitStereochemistry_predicate1(SliceParser.Stereochemistry_predicate1Context ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#stereochemistry_predicate2}.
	 * @param ctx the parse tree
	 */
	void enterStereochemistry_predicate2(SliceParser.Stereochemistry_predicate2Context ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#stereochemistry_predicate2}.
	 * @param ctx the parse tree
	 */
	void exitStereochemistry_predicate2(SliceParser.Stereochemistry_predicate2Context ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#stereochemistry_predicate3}.
	 * @param ctx the parse tree
	 */
	void enterStereochemistry_predicate3(SliceParser.Stereochemistry_predicate3Context ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#stereochemistry_predicate3}.
	 * @param ctx the parse tree
	 */
	void exitStereochemistry_predicate3(SliceParser.Stereochemistry_predicate3Context ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_stmt}.
	 * @param ctx the parse tree
	 */
	void enterLoop_stmt(SliceParser.Loop_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_stmt}.
	 * @param ctx the parse tree
	 */
	void exitLoop_stmt(SliceParser.Loop_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject(SliceParser.Loop_subjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject(SliceParser.Loop_subjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_atom_type}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_atom_type(SliceParser.Loop_subject_atom_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_atom_type}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_atom_type(SliceParser.Loop_subject_atom_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_atom_property}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_atom_property(SliceParser.Loop_subject_atom_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_atom_property}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_atom_property(SliceParser.Loop_subject_atom_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_atom_combined}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_atom_combined(SliceParser.Loop_subject_atom_combinedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_atom_combined}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_atom_combined(SliceParser.Loop_subject_atom_combinedContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_bond_property}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_bond_property(SliceParser.Loop_subject_bond_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_bond_property}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_bond_property(SliceParser.Loop_subject_bond_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_group_property}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_group_property(SliceParser.Loop_subject_group_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_group_property}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_group_property(SliceParser.Loop_subject_group_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_functional_group_property}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_functional_group_property(SliceParser.Loop_subject_functional_group_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_functional_group_property}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_functional_group_property(SliceParser.Loop_subject_functional_group_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_non_functional_group_property}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_non_functional_group_property(SliceParser.Loop_subject_non_functional_group_propertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_non_functional_group_property}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_non_functional_group_property(SliceParser.Loop_subject_non_functional_group_propertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_subject_atom_locs}.
	 * @param ctx the parse tree
	 */
	void enterLoop_subject_atom_locs(SliceParser.Loop_subject_atom_locsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_subject_atom_locs}.
	 * @param ctx the parse tree
	 */
	void exitLoop_subject_atom_locs(SliceParser.Loop_subject_atom_locsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_predicate}.
	 * @param ctx the parse tree
	 */
	void enterLoop_predicate(SliceParser.Loop_predicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_predicate}.
	 * @param ctx the parse tree
	 */
	void exitLoop_predicate(SliceParser.Loop_predicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#loop_predicate_ring}.
	 * @param ctx the parse tree
	 */
	void enterLoop_predicate_ring(SliceParser.Loop_predicate_ringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#loop_predicate_ring}.
	 * @param ctx the parse tree
	 */
	void exitLoop_predicate_ring(SliceParser.Loop_predicate_ringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#set_current_ring}.
	 * @param ctx the parse tree
	 */
	void enterSet_current_ring(SliceParser.Set_current_ringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#set_current_ring}.
	 * @param ctx the parse tree
	 */
	void exitSet_current_ring(SliceParser.Set_current_ringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom_on_bond}.
	 * @param ctx the parse tree
	 */
	void enterAtom_on_bond(SliceParser.Atom_on_bondContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom_on_bond}.
	 * @param ctx the parse tree
	 */
	void exitAtom_on_bond(SliceParser.Atom_on_bondContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#bond_on_atom}.
	 * @param ctx the parse tree
	 */
	void enterBond_on_atom(SliceParser.Bond_on_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#bond_on_atom}.
	 * @param ctx the parse tree
	 */
	void exitBond_on_atom(SliceParser.Bond_on_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(SliceParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(SliceParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#variable_name}.
	 * @param ctx the parse tree
	 */
	void enterVariable_name(SliceParser.Variable_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#variable_name}.
	 * @param ctx the parse tree
	 */
	void exitVariable_name(SliceParser.Variable_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#defined_as}.
	 * @param ctx the parse tree
	 */
	void enterDefined_as(SliceParser.Defined_asContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#defined_as}.
	 * @param ctx the parse tree
	 */
	void exitDefined_as(SliceParser.Defined_asContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#where_stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhere_stmt(SliceParser.Where_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#where_stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhere_stmt(SliceParser.Where_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#ring_prefix_prop}.
	 * @param ctx the parse tree
	 */
	void enterRing_prefix_prop(SliceParser.Ring_prefix_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#ring_prefix_prop}.
	 * @param ctx the parse tree
	 */
	void exitRing_prefix_prop(SliceParser.Ring_prefix_propContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#ring_suffix_prop}.
	 * @param ctx the parse tree
	 */
	void enterRing_suffix_prop(SliceParser.Ring_suffix_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#ring_suffix_prop}.
	 * @param ctx the parse tree
	 */
	void exitRing_suffix_prop(SliceParser.Ring_suffix_propContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#ring_size}.
	 * @param ctx the parse tree
	 */
	void enterRing_size(SliceParser.Ring_sizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#ring_size}.
	 * @param ctx the parse tree
	 */
	void exitRing_size(SliceParser.Ring_sizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#alpha_num}.
	 * @param ctx the parse tree
	 */
	void enterAlpha_num(SliceParser.Alpha_numContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#alpha_num}.
	 * @param ctx the parse tree
	 */
	void exitAlpha_num(SliceParser.Alpha_numContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#numeric_num}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_num(SliceParser.Numeric_numContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#numeric_num}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_num(SliceParser.Numeric_numContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atomtype}.
	 * @param ctx the parse tree
	 */
	void enterAtomtype(SliceParser.AtomtypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atomtype}.
	 * @param ctx the parse tree
	 */
	void exitAtomtype(SliceParser.AtomtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#functional_group}.
	 * @param ctx the parse tree
	 */
	void enterFunctional_group(SliceParser.Functional_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#functional_group}.
	 * @param ctx the parse tree
	 */
	void exitFunctional_group(SliceParser.Functional_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#non_functional_group}.
	 * @param ctx the parse tree
	 */
	void enterNon_functional_group(SliceParser.Non_functional_groupContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#non_functional_group}.
	 * @param ctx the parse tree
	 */
	void exitNon_functional_group(SliceParser.Non_functional_groupContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#smarts}.
	 * @param ctx the parse tree
	 */
	void enterSmarts(SliceParser.SmartsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#smarts}.
	 * @param ctx the parse tree
	 */
	void exitSmarts(SliceParser.SmartsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom_loc}.
	 * @param ctx the parse tree
	 */
	void enterAtom_loc(SliceParser.Atom_locContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom_loc}.
	 * @param ctx the parse tree
	 */
	void exitAtom_loc(SliceParser.Atom_locContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom_properties}.
	 * @param ctx the parse tree
	 */
	void enterAtom_properties(SliceParser.Atom_propertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom_properties}.
	 * @param ctx the parse tree
	 */
	void exitAtom_properties(SliceParser.Atom_propertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom_or_bond_properties}.
	 * @param ctx the parse tree
	 */
	void enterAtom_or_bond_properties(SliceParser.Atom_or_bond_propertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom_or_bond_properties}.
	 * @param ctx the parse tree
	 */
	void exitAtom_or_bond_properties(SliceParser.Atom_or_bond_propertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#bond_properties}.
	 * @param ctx the parse tree
	 */
	void enterBond_properties(SliceParser.Bond_propertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#bond_properties}.
	 * @param ctx the parse tree
	 */
	void exitBond_properties(SliceParser.Bond_propertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#atom_or_bond_or_group_properties}.
	 * @param ctx the parse tree
	 */
	void enterAtom_or_bond_or_group_properties(SliceParser.Atom_or_bond_or_group_propertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#atom_or_bond_or_group_properties}.
	 * @param ctx the parse tree
	 */
	void exitAtom_or_bond_or_group_properties(SliceParser.Atom_or_bond_or_group_propertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#group_properties}.
	 * @param ctx the parse tree
	 */
	void enterGroup_properties(SliceParser.Group_propertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#group_properties}.
	 * @param ctx the parse tree
	 */
	void exitGroup_properties(SliceParser.Group_propertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#charge_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCharge_stmt(SliceParser.Charge_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#charge_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCharge_stmt(SliceParser.Charge_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#centre_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCentre_stmt(SliceParser.Centre_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#centre_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCentre_stmt(SliceParser.Centre_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#ring_prop}.
	 * @param ctx the parse tree
	 */
	void enterRing_prop(SliceParser.Ring_propContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#ring_prop}.
	 * @param ctx the parse tree
	 */
	void exitRing_prop(SliceParser.Ring_propContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#alkyl_number}.
	 * @param ctx the parse tree
	 */
	void enterAlkyl_number(SliceParser.Alkyl_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#alkyl_number}.
	 * @param ctx the parse tree
	 */
	void exitAlkyl_number(SliceParser.Alkyl_numberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(SliceParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(SliceParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#notcontain}.
	 * @param ctx the parse tree
	 */
	void enterNotcontain(SliceParser.NotcontainContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#notcontain}.
	 * @param ctx the parse tree
	 */
	void exitNotcontain(SliceParser.NotcontainContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#contain}.
	 * @param ctx the parse tree
	 */
	void enterContain(SliceParser.ContainContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#contain}.
	 * @param ctx the parse tree
	 */
	void exitContain(SliceParser.ContainContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#notequal}.
	 * @param ctx the parse tree
	 */
	void enterNotequal(SliceParser.NotequalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#notequal}.
	 * @param ctx the parse tree
	 */
	void exitNotequal(SliceParser.NotequalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#equal}.
	 * @param ctx the parse tree
	 */
	void enterEqual(SliceParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#equal}.
	 * @param ctx the parse tree
	 */
	void exitEqual(SliceParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#same}.
	 * @param ctx the parse tree
	 */
	void enterSame(SliceParser.SameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#same}.
	 * @param ctx the parse tree
	 */
	void exitSame(SliceParser.SameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(SliceParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(SliceParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(SliceParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(SliceParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by {@link SliceParser#smarts_char}.
	 * @param ctx the parse tree
	 */
	void enterSmarts_char(SliceParser.Smarts_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link SliceParser#smarts_char}.
	 * @param ctx the parse tree
	 */
	void exitSmarts_char(SliceParser.Smarts_charContext ctx);
}