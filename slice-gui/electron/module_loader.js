/*<script src="field_textbutton.js"></script>
  <script src="custom_category.js"></script>

 
  <script type="module" src="sliceBlock.js"></script>
  <script type="module" src="sliceCode.js"></script>
  <script type="module" src="sliceGenerator.js"></script>*/

import * as variables_definition from './editor/variable_definition.js';
import * as icon_utils from './editor/icon_utils.js';
import * as editor_utils from './editor/editor_utils.js';
import * as periodic_table from './editor/periodic_table.js';
import * as kekule_editor from './editor/kekule_editor.js';
import * as pattern_editor from './editor/pattern_editor.js';
import * as description_tab from './editor/description_tab.js';
import * as pattern_tab from './editor/pattern_tab.js';
import * as condition_tab from './editor/condition_tab.js';
import * as logic_assistant_tab from './editor/logic_assistant_tab.js';
import * as pattern_generator from './editor/pattern_generator.js';
import * as smartsUtils from './editor/smarts_utils.js';
import * as sliceFile_generator from './editor/sliceFile_generator.js';
import * as slice_blockly from './editor/slice_blockly.js';
import * as framework_specific_functions from './editor/electron_specific_functions.js';

import * as en from './blockly/msg/js/en.js';

import * as field_textbutton from './blockly/utils/field_textbutton.js';
import * as custom_category from './blockly/utils/custom_category.js';
import * as custom_dialog from './blockly/utils/custom-dialog.js';

import * as slice_block from './blockly/slice/sliceBlock.js';
import * as slice_code from './blockly/slice/sliceCode.js';
import * as slice_generator from './blockly/slice/sliceGenerator.js';

import {createMinusField} from './blockly/block-plus-minus/field_minus.js';
import {createPlusField} from './blockly/block-plus-minus/field_plus.js';

//blocks
import * as if_blocks from './blockly/slice/blocks/if.js';
import * as andOrIf_blocks from './blockly/slice/blocks/andorif.js';
import * as andOr_blocks from './blockly/slice/blocks/andor.js';
import * as procedures_blocks from './blockly/slice/blocks/procedures.js';
import * as logic_blocks from './blockly/slice/blocks/logic.js';
import * as subjects_blocks from './blockly/slice/blocks/subjects.js';
import * as predicates_blocks from './blockly/slice/blocks/predicates.js';
import * as actions_blocks from './blockly/slice/blocks/actions.js';
import * as statements_blocks from './blockly/slice/blocks/statements.js';
import * as imports_blocks from './blockly/slice/blocks/imports.js';
import * as loops_blocks from './blockly/slice/blocks/loops.js';
import * as variables_blocks from './blockly/slice/blocks/variables.js';
import * as comments_blocks from './blockly/slice/blocks/comments.js';
import * as math_blocks from './blockly/slice/blocks/math.js';

//generators
import * as statements from './blockly/slice/generators/statements.js';
import * as logic from './blockly/slice/generators/logic.js';
import * as subjects from './blockly/slice/generators/subjects.js';
import * as predicates from './blockly/slice/generators/predicates.js';
import * as actions from './blockly/slice/generators/actions.js';
import * as math from './blockly/slice/generators/math.js';
import * as imports from './blockly/slice/generators/imports.js';
import * as loops from './blockly/slice/generators/loops.js';
import * as variables from './blockly/slice/generators/variables.js';
import * as variablesDynamic from './blockly/slice/generators/variables_dynamic.js';
import * as procedures from './blockly/slice/generators/procedures.js';
import * as comments from './blockly/slice/generators/comments.js';

console.log("module imported")
