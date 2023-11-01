/**
 * <p>
 * 消息
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-30 14:37
 */

import { memo } from 'react'
import { ChatWindowSenderAvatar } from '../SenderAvatar/ChatWindowSenderAvatar'
import { MessageBubble } from '../MessageBubble'
import { MessageTypeEnum } from '#/constant/MessageTypeEnum'

interface MessageItemProps {
  // 是否是自己
  isSelf: boolean
}

export const MessageItem = memo<MessageItemProps>(function MessageItem(props) {
  const order = props.isSelf ? 'flex-row-reverse space-x-reverse' : ''

  return (
    <div
      className={`flex p-4  space-x-2 ${order}`}
      style={{ contentVisibility: 'auto' }}>
      <ChatWindowSenderAvatar className={`flex-shrink-0`} />

      <div className="max-w-4xl">
        <MessageBubble msgType={MessageTypeEnum.Text} />
      </div>
    </div>
  )
})
