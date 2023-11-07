/**
 * <p>
 * a1
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 14:20
 */
import { EditorEventsObserver } from '.'
import { SubjectType } from '../subject-type'

type Callback = (e: React.FormEvent<HTMLDivElement>) => void
export class EditorInputEventObserverImpl
  implements EditorEventsObserver<React.FormEvent<HTMLDivElement>>
{
  private _callback: Callback
  constructor(callback: Callback) {
    this._callback = callback
  }

  invoke(data: React.FormEvent<HTMLDivElement>): void {
    this._callback(data)
  }

  canHandle(type: SubjectType): boolean {
    return type === SubjectType.EDITOR_INPUT_CHANGE
  }
}
