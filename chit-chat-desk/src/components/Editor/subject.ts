/**
 * <p>
 * 订阅主题
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-11-07 13:12
 */

import { EditorEventsObserver } from './observer'
import { SubjectType } from './subject-type'

export type EditorSubjectNotifi<T = any> = {
  type: SubjectType
  data: T
}

export interface Subject {
  registerObserver(observer: EditorEventsObserver): void
  unregisterObserver(observer: EditorEventsObserver): void
  notifyObservers(data: EditorSubjectNotifi): void
}

class EditorSubject implements Subject {
  private _observers = new Set<EditorEventsObserver>()

  registerObserver(observer: EditorEventsObserver): void {
    if (this._observers.has(observer)) {
      throw new Error('Observer already registered.')
    }
    this._observers.add(observer)
  }

  unregisterObserver(observer: EditorEventsObserver): void {
    this._observers.delete(observer)
  }

  notifyObservers(event: EditorSubjectNotifi): void {
    this._observers.forEach((observer) => {
      if (observer.canHandle(event.type)) {
        observer.invoke(event.data)
      }
    })
  }
}

export const editorSubjectInstance = new EditorSubject()
