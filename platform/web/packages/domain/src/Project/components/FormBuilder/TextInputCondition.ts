import { ConditionBlock, Forms, affects, definition, editor, tripetto } from "@tripetto/builder";
import "@tripetto/block-checkbox";
import "@tripetto/block-date";
import "@tripetto/block-text";
import icon from "./icon.svg";
import { TextInputBlock } from "./TextInputBlock";

@tripetto({
    type: "condition",
    context: TextInputBlock,
    identifier: "text-input-condition",
    label: "Text input match",
    icon
})
export class TextInputCondition extends ConditionBlock {
    @definition
    @affects("#name")
    value = "";

    get name() {
        return this.value || this.type.label;
    }


    @editor
    onEdit(): void {
        this.editor.form({
            title: "Value to check for",
            controls: [
                new Forms.Text("singleline", Forms.Text.bind(this, "value", ""))
                    .placeholder("Enter the value to match here...")
                    .autoFocus()
            ]
        });
    }
}
