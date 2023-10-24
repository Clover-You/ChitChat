/**
 * <p>
 * 聊天面板
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 14:35
 */

import { MessageList } from '../Chat/MessageList'

export function ChatPanel() {
  return (
    <div className="chat-panel container h-full">
      <div className="chat-panel-content">
        <div className="chat-panel-content-header"></div>
        <div className="chat-panel-content-body">
          <MessageList />
        </div>
      </div>
    </div>
  )
}
