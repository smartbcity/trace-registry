import { tripetto, slots, definition, editor, isBoolean, NodeBlock, Slots, Forms, conditions } from "@tripetto/builder";
import icon from "./icon.svg";
import { TextInputCondition } from "./TextInputCondition";

@tripetto({
  type: "node",
  identifier: "text-input",
  label: "Text input",
  icon
})
export class TextInputBlock extends NodeBlock {
  @definition
  isMultiline?: boolean;

  // Add a field that holds a reference to the slot
  valueSlot!: Slots.String;

  @slots
  onSlots(): void {
    this.valueSlot = this.slots.static({
      type: Slots.String,
      reference: "value",
      label: "Text input value"
    });
  }

  @editor
  onEdit(): void {
    // First add the general group title (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#groups)
    this.editor.groups.general();
    // Add the name feature (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#name)
    this.editor.name();
    // Add the placeholder feature (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#placeholder)
    this.editor.placeholder();

    // Add the settings group title (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#groups)
    this.editor.groups.settings();
    // Add the required feature (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#required)
    this.editor.required(this.valueSlot);
    // Add the alias feature (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#alias)
    this.editor.alias(this.valueSlot);
    // Add the exportable feature (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#exportable)
    this.editor.exportable(this.valueSlot);

    // Add a custom feature to toggle the text input mode (see https://tripetto.com/sdk/docs/blocks/api/classes/EditorOrchestrator#option)
    this.editor.option({
      name: "Input mode",
      form: {
        title: "Text input mode",
        controls: [
          new Forms.Checkbox(
            "Allow multi-line text input",
            Forms.Checkbox.bind(this, "isMultiline", false, true)
          )
        ],
      },
      activated: isBoolean(this.isMultiline)
    });
  }

  @conditions
  onConditions(): void {
    this.conditions.template({
      condition: TextInputCondition,
      props: {
        slot: this.valueSlot
      },
      autoOpen: true
    });
  }
}
